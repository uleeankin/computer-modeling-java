package com.uleeankin.computermodelingjava.lab6;

import javafx.collections.ObservableList;

import java.util.List;

public class MathStatisticCalculator {

    private final double quantile = 1.96;


    public ConfidenceInterval calculateConfidenceInterval(
            List<Double> results) {

        int sampleSize = results.size();
        double mathDelay = getMathDelay(results, sampleSize);
        double deviation = Math.sqrt(getDispersion(results, mathDelay, sampleSize));


        double accuracy = this.quantile * (deviation / Math.sqrt(sampleSize));
        return new ConfidenceInterval(mathDelay + accuracy, mathDelay - accuracy);
    }

    private double getMathDelay(List<Double> results, int sampleSize) {

        double sum = 0;
        for (double result : results) {
            sum += result;
        }

        return sum / sampleSize;
    }

    private double getDispersion(List<Double> results,
                                 double mathDelay, int sampleSize) {

        double sum = 0;
        for (double result : results) {
            sum += Math.pow(result - mathDelay, 2);
        }

        return sum / sampleSize;
    }
}
