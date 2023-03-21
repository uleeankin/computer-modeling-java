package com.uleeankin.computermodelingjava.lab7;

import com.uleeankin.computermodelingjava.utils.ControlsDataDisplayer;
import com.uleeankin.computermodelingjava.utils.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lab7Controller {

    private final int SPLIT_SECTION_NUMBER = 15;
    private final double QUANTILE = 1.96;

    @FXML
    private TextField leftScreenTextField;

    @FXML
    private TextField rightScreenTextField;

    @FXML
    private TextField testNumber;

    @FXML
    private TextField experimentNumber;

    @FXML
    private TextField initialPosition;

    @FXML
    private TextField rightStepProbability;

    @FXML
    private TextField accuracy;

    @FXML
    private Group histogramGroup;

    @FXML
    private Group functionGroup;

    @FXML
    private Label dispersion;

    @FXML
    private Label firstPartSampleSize;

    @FXML
    private Label secondPartSampleSize;

    @FXML
    private VBox partsBox;

    @FXML
    protected void onStartButtonClick() {

        double leftScreenPosition = Parser.parseTextFieldValueToDouble(
                                            this.leftScreenTextField);

        double rightScreenPosition = Parser.parseTextFieldValueToDouble(
                                            this.rightScreenTextField);

        int testNumber = Parser.parseTextFieldValueToInt(this.testNumber);
        int experimentsNumber = Parser.parseTextFieldValueToInt(this.experimentNumber);
        double initialPosition = Parser.parseTextFieldValueToDouble(this.initialPosition);
        double rightStepProbability = Parser.parseTextFieldValueToDouble(
                                            this.rightStepProbability);
        double accuracy = Parser.parseTextFieldValueToDouble(
                this.accuracy);

        ObservableList<Double> experimentResults = FXCollections.observableArrayList();
        int threadsNumber = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);

        try {
            experimentResults = this.executeExperiments(experimentsNumber,
                    testNumber, leftScreenPosition, rightScreenPosition,
                    initialPosition, rightStepProbability, executor, threadsNumber);

            terminateExecutorService(executor);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            shutDownExecutorService(executor);
        }

        if (!experimentResults.isEmpty()) {

            this.showDiagrams(experimentResults, experimentsNumber);
            this.partsBox.setVisible(true);

            MathStatisticCalculator calculator = new MathStatisticCalculator();
            double dispersion = calculator.getDispersion(experimentResults);
            double dispersionProximity = accuracy / dispersion;

            this.dispersion.setText(
                    String.valueOf(dispersion));
            this.firstPartSampleSize.setText(
                    String.valueOf(
                            calculator.getFirstPartSampleSize(dispersion, this.QUANTILE, accuracy)));

            this.secondPartSampleSize.setText(
                    String.valueOf(
                            calculator.getSecondPartSampleSize(this.QUANTILE, dispersionProximity)));
        }
    }

    private ObservableList<Double> executeExperiments(int experimentsNumber,
                                                      int testNumber,
                                                      double leftScreenPosition,
                                                      double rightScreenPosition,
                                                      double initialPosition,
                                                      double rightStepProbability,
                                                      ExecutorService executor,
                                                      int threadsNumber)
            throws ExecutionException, InterruptedException {

        return new CalculationExecutor(
                executor, experimentsNumber, testNumber, threadsNumber,
                leftScreenPosition, rightScreenPosition, initialPosition,
                rightStepProbability).execute();
    }

    private void showDiagrams(ObservableList<Double> results, int sampleSize) {
        ControlsDataDisplayer.buildFrequencyHistogram(
                results, this.SPLIT_SECTION_NUMBER, sampleSize,
                this.histogramGroup, false, "Гистограмма частот");
        ControlsDataDisplayer.buildStatisticalFunction(
                results, this.SPLIT_SECTION_NUMBER, sampleSize,
                this.functionGroup, false, "Статистическая функция");
    }

    private void terminateExecutorService(ExecutorService executor) throws InterruptedException {
        executor.shutdown();
    }

    private void shutDownExecutorService(ExecutorService executor) {
        executor.shutdownNow();
    }

}
