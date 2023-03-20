package com.uleeankin.computermodelingjava.lab7;

import com.uleeankin.computermodelingjava.utils.ControlsDataDisplayer;
import com.uleeankin.computermodelingjava.utils.Parser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    private Label averageWalkTime;

    @FXML
    private VBox averageWalkTimeBox;

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
        double initialPosition = Parser.parseTextFieldValueToDouble(this.initialPosition);
        double rightStepProbability = Parser.parseTextFieldValueToDouble(
                                            this.rightStepProbability);

        double accuracy = Parser.parseTextFieldValueToDouble(
                this.accuracy);

        RandomWalkSimulator simulator = new RandomWalkSimulator();

        ObservableList<Double> randomWalkTime = simulator.getRandomWalksTime(testNumber,
                leftScreenPosition, rightScreenPosition, initialPosition, rightStepProbability);

        this.averageWalkTimeBox.setVisible(true);
        this.averageWalkTime.setText(
                String.valueOf(simulator.getAverageWalkTime(randomWalkTime)));

        ControlsDataDisplayer.buildFrequencyHistogram(
                randomWalkTime, this.SPLIT_SECTION_NUMBER, testNumber,
                this.histogramGroup, false, "Гистограмма частот");
        ControlsDataDisplayer.buildStatisticalFunction(
                randomWalkTime, this.SPLIT_SECTION_NUMBER, testNumber,
                this.functionGroup, false, "Статистическая функция");

        this.partsBox.setVisible(true);

        MathStatisticCalculator calculator = new MathStatisticCalculator();
        double dispersion = calculator.getDispersion(randomWalkTime);
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
