package com.uleeankin.computermodelingjava;

import com.uleeankin.computermodelingjava.beta.BetaDistributionGenerator;
import com.uleeankin.computermodelingjava.beta.BetaParametersCalculator;
import com.uleeankin.computermodelingjava.utils.ControlsDataDisplayer;
import com.uleeankin.computermodelingjava.utils.Parser;
import com.uleeankin.computermodelingjava.weibull.WeibullDistributionGenerator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;

public class Lab6Controller {

    @FXML
    private TextField sampleSizeValue;

    @FXML
    private TextField m;

    @FXML
    private TextField p;

    @FXML
    private TextField b;

    @FXML
    private TextField c;

    @FXML
    private Label betaMathDelay;

    @FXML
    private Label betaDispersion;

    @FXML
    private Label weibullMathDelay;

    @FXML
    private Label weibullDispersion;

    @FXML
    private ListView<Double> betaRandomValuesView;

    @FXML
    private ListView<Double> weibullRandomValuesView;

    @FXML
    private Group betaFrequencyHistogramGroup;

    @FXML
    private Group betaStatisticalFunctionGroup;

    @FXML
    private Group weibullFrequencyHistogramGroup;

    @FXML
    private Group weibullStatisticalFunctionGroup;

    @FXML
    private RadioButton splitSectionNumbers15;

    @FXML
    private RadioButton splitSectionNumbers25;

    private int splitSectionNumber = 15;

    @FXML
    protected void onClickRadioButton() {
        if (this.splitSectionNumbers15.isArmed()) {
            this.splitSectionNumbers15.setSelected(true);
            this.splitSectionNumbers25.setSelected(false);
            this.splitSectionNumber = 15;
        } else {
            this.splitSectionNumbers15.setSelected(false);
            this.splitSectionNumbers25.setSelected(true);
            this.splitSectionNumber = 25;
        }
    }

    @FXML
    protected void onGenerateButtonClick() {

        int sampleSize = Parser.parseTextFieldValueToInt(sampleSizeValue);
        int m = Parser.parseTextFieldValueToInt(this.m);
        double p = Parser.parseTextFieldValueToDouble(this.p);
        int b = Parser.parseTextFieldValueToInt(this.b);
        int c = Parser.parseTextFieldValueToInt(this.c);

        this.getBetaDistribution(sampleSize, m, p);
        this.getWeibullDistribution(sampleSize, b, c);
    }

    private void getWeibullDistribution(int sampleSize, int b, int c) {
        WeibullDistributionGenerator weibullDistributionGenerator =
                new WeibullDistributionGenerator(sampleSize, b, c);

        ObservableList<Double> randomValues = weibullDistributionGenerator.generate();
        ControlsDataDisplayer.showRandomValues(
                randomValues, this.weibullRandomValuesView);

        ControlsDataDisplayer.buildFrequencyHistogram(
                randomValues, this.splitSectionNumber, sampleSize,
                this.weibullFrequencyHistogramGroup, false, "Вейбулла. Гистограмма частот");
        ControlsDataDisplayer.buildStatisticalFunction(
                randomValues, this.splitSectionNumber, sampleSize,
                this.weibullStatisticalFunctionGroup, false, "Вейбулла. Статистическая функция");
    }

    private void getBetaDistribution(int sampleSize, int m, double p) {
        BetaDistributionGenerator betaDistributionGenerator
                = new BetaDistributionGenerator(sampleSize, m, p);

        ObservableList<Double> randomValues = betaDistributionGenerator.generate();
        ControlsDataDisplayer.showRandomValues(
                randomValues, this.betaRandomValuesView);

        BetaParametersCalculator parametersCalculator
                = new BetaParametersCalculator(m, p);

        double mathDelay = parametersCalculator.getMathDelay();
        double dispersion = parametersCalculator.getDispersion();

        this.betaMathDelay.setText(String.valueOf(mathDelay));
        this.betaDispersion.setText(String.valueOf(dispersion));

        ControlsDataDisplayer.buildFrequencyHistogram(
                randomValues, this.splitSectionNumber, sampleSize,
                this.betaFrequencyHistogramGroup, true, "Beta. Гистограмма частот");
        ControlsDataDisplayer.buildStatisticalFunction(
                randomValues, this.splitSectionNumber, sampleSize,
                this.betaStatisticalFunctionGroup, true, "Beta. Статистическая функция");
    }


}