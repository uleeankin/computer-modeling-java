package com.uleeankin.computermodelingjava.lab6;

import com.uleeankin.computermodelingjava.utils.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Lab6Controller implements Initializable {

    @FXML
    private TextField probabilityTextField;

    @FXML
    private TextField experimentsNumberTextField;

    @FXML
    private TextField experimentsNumber;

    @FXML
    private TableView<AllExperimentsResult> table;

    @FXML
    private Label theoreticalAProbabilityLabel;

    @FXML
    private Label theoreticalBProbabilityLabel;

    @FXML
    private Label theoreticalCProbabilityLabel;

    @FXML
    private Label theoreticalDProbabilityLabel;

    @FXML
    private Label realAProbabilityLabel;

    @FXML
    private Label realBProbabilityLabel;

    @FXML
    private Label realCProbabilityLabel;

    @FXML
    private Label realDProbabilityLabel;

    private ObservableList<AllExperimentsResult> tableData = FXCollections.observableArrayList();

    @FXML
    protected void onTheoreticalProbabilitiesButtonClick() {
        TheoreticalProbabilityCalculator calculator
                = new TheoreticalProbabilityCalculator();

        double p = 1d - Parser.parseTextFieldValueToDouble(this.probabilityTextField);

        this.theoreticalAProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalAProbability(p)));

        this.theoreticalBProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalBProbability(p)));

        this.theoreticalCProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalCProbability(p)));

        this.theoreticalDProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalDProbability(p)));
    }

    @FXML
    protected void onExperimentsButtonClick() {
        double p = 1 - Parser.parseTextFieldValueToDouble(this.probabilityTextField);
        int n = Parser.parseTextFieldValueToInt(this.experimentsNumberTextField);
        int s = Parser.parseTextFieldValueToInt(this.experimentsNumber);

        MonteCarloModulator modulator = new MonteCarloModulator();
        this.tableData.clear();
        for (int i = 0; i < s; i++) {
            ObservableList<ExperimentResult> results = FXCollections.observableArrayList();
            results =  modulator.startExperiment(n, results);
            double[] probabilities = modulator.countProbability(n, p, results);

            this.tableData.add(new AllExperimentsResult(probabilities[0],
                    probabilities[1], probabilities[2], probabilities[3]));
        }

        MathStatisticCalculator mathStatisticCalculator
                = new MathStatisticCalculator();
        ConfidenceInterval confidenceInterval =
                mathStatisticCalculator.calculateConfidenceInterval(getAProbabilities(this.tableData));

        this.realAProbabilityLabel.setText("{ " +
                confidenceInterval.getNegative() + " ; " +
                confidenceInterval.getPositive() + " }");

        confidenceInterval =
                mathStatisticCalculator.calculateConfidenceInterval(getBProbabilities(this.tableData));

        this.realBProbabilityLabel.setText("{ " +
                confidenceInterval.getNegative() + " ; " +
                confidenceInterval.getPositive() + " }");

        confidenceInterval =
                mathStatisticCalculator.calculateConfidenceInterval(getCProbabilities(this.tableData));

        this.realCProbabilityLabel.setText("{ " +
                confidenceInterval.getNegative() + " ; " +
                confidenceInterval.getPositive() + " }");

        confidenceInterval =
                mathStatisticCalculator.calculateConfidenceInterval(getDProbabilities(this.tableData));

        this.realDProbabilityLabel.setText("{ " +
                confidenceInterval.getNegative() + " ; " +
                confidenceInterval.getPositive() + " }");
    }

    private List<Double> getAProbabilities(ObservableList<AllExperimentsResult> results) {
        List<Double> aProbabilities = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            aProbabilities.add(results.get(i).getPa());
        }
        return aProbabilities;
    }

    private List<Double> getCProbabilities(ObservableList<AllExperimentsResult> results) {
        List<Double> cProbabilities = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            cProbabilities.add(results.get(i).getPc());
        }
        return cProbabilities;
    }

    private List<Double> getBProbabilities(ObservableList<AllExperimentsResult> results) {
        List<Double> bProbabilities = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            bProbabilities.add(results.get(i).getPb());
        }
        return bProbabilities;
    }

    private List<Double> getDProbabilities(ObservableList<AllExperimentsResult> results) {
        List<Double> dProbabilities = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            dProbabilities.add(results.get(i).getPd());
        }
        return dProbabilities;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn p1 = new TableColumn("pa");
        p1.setCellValueFactory(new PropertyValueFactory<>("pa"));
        TableColumn p2 = new TableColumn<>("pb");
        p2.setCellValueFactory(new PropertyValueFactory<>("pb"));
        TableColumn p3 = new TableColumn<>("pc");
        p3.setCellValueFactory(new PropertyValueFactory<>("pc"));
        TableColumn p4 = new TableColumn<>("pd");
        p4.setCellValueFactory(new PropertyValueFactory<>("pd"));


        this.table.getColumns().addAll(p1, p2, p3, p4);
        this.table.setItems(this.tableData);
    }
}
