package com.uleeankin.computermodelingjava.lab5.weibull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class WeibullDistributionGenerator {

    private final int sampleSize;

    private final int b;

    private final int c;

    public WeibullDistributionGenerator(int sampleSize, int b, int c) {
        this.sampleSize = sampleSize;
        this.b = b;
        this.c = c;
    }

    public ObservableList<Double> generate() {
        ObservableList<Double> generatedValues = FXCollections.observableArrayList();
        Random random = new Random();

        for (int i = 0; i < this.sampleSize; i++) {
            double r = random.nextDouble();
            generatedValues.add(
                this.b * Math.pow(-Math.log(r), 1d / this.c)
            );
        }

        return generatedValues;
    }
}
