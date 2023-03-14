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
import java.util.List;
import java.util.ResourceBundle;

public class Lab6Controller implements Initializable {

    @FXML
    private TextField probabilityTextField;

    @FXML
    private TextField experimentsNumberTextField;

    @FXML
    private TableView<ExperimentResult> table;

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

    private ObservableList<ExperimentResult> tableData = FXCollections.observableArrayList();

    @FXML
    protected void onTheoreticalProbabilitiesButtonClick() {
        TheoreticalProbabilityCalculator calculator
                = new TheoreticalProbabilityCalculator();

        double p = 1d - Parser.parseTextFieldValueToDouble(this.probabilityTextField);

        theoreticalAProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalAProbability(p)));

        theoreticalBProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalBProbability(p)));

        theoreticalCProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalCProbability(p)));

        theoreticalDProbabilityLabel.setText(
                String.valueOf(calculator.getTheoreticalDProbability(p)));
    }

    @FXML
    protected void onExperimentsButtonClick() {
        double p = 1 - Parser.parseTextFieldValueToDouble(this.probabilityTextField);
        int n = Parser.parseTextFieldValueToInt(this.experimentsNumberTextField);

        MonteCarloModulator modulator = new MonteCarloModulator();
        this.tableData.clear();
        this.tableData = modulator.startExperiment(n, tableData);

        double[] probabilities = modulator.countProbability(n, p, this.tableData);

        this.realAProbabilityLabel.setText(String.valueOf(probabilities[0]));
        this.realBProbabilityLabel.setText(String.valueOf(probabilities[1]));
        this.realCProbabilityLabel.setText(String.valueOf(probabilities[2]));
        this.realDProbabilityLabel.setText(String.valueOf(probabilities[3]));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn p1 = new TableColumn("p1");
        p1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        TableColumn p2 = new TableColumn<>("p2");
        p2.setCellValueFactory(new PropertyValueFactory<>("p2"));
        TableColumn p3 = new TableColumn<>("p3");
        p3.setCellValueFactory(new PropertyValueFactory<>("p3"));
        TableColumn p4 = new TableColumn<>("p4");
        p4.setCellValueFactory(new PropertyValueFactory<>("p4"));
        TableColumn p5 = new TableColumn<>("p5");
        p5.setCellValueFactory(new PropertyValueFactory<>("p5"));
        TableColumn p6 = new TableColumn<>("p6");
        p6.setCellValueFactory(new PropertyValueFactory<>("p6"));
        TableColumn p7 = new TableColumn<>("p7");
        p7.setCellValueFactory(new PropertyValueFactory<>("p7"));
        TableColumn p8 = new TableColumn<>("p8");
        p8.setCellValueFactory(new PropertyValueFactory<>("p8"));
        TableColumn p9 = new TableColumn<>("p9");
        p9.setCellValueFactory(new PropertyValueFactory<>("p9"));
        TableColumn p10 = new TableColumn<>("p10");
        p10.setCellValueFactory(new PropertyValueFactory<>("p10"));

        this.table.getColumns().addAll(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        this.table.setItems(this.tableData);
    }
}
