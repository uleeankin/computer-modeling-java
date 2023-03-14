package com.uleeankin.computermodelingjava.lab5.beta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class BetaDistributionGenerator {

    private final int sampleSize;

    private final int m;

    private final double p;

    public BetaDistributionGenerator(int sampleSize, int m, double p) {
        this.sampleSize = sampleSize;
        this.m = m;
        this.p = p;
    }

    // generate random values with beta-distribution
    public ObservableList<Double> generate() {
        ObservableList<Double> randomValues = FXCollections.observableArrayList();
        Random random = new Random();
        double s;
        double r;

        for (int i = 0; i < this.sampleSize; i++) {
            s = 0;
            for (int j = 0; j < this.m; j++) {
                r = random.nextDouble();
                s += (Math.log(r) / (this.p + j));
            }
            randomValues.add(Math.exp(s));
        }

        return randomValues;
    }
}
