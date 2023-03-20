package com.uleeankin.computermodelingjava.lab7;

import javafx.collections.ObservableList;

public class MathStatisticCalculator {

    public double getDispersion(ObservableList<Double> sample) {


        double sum = 0d;
        for (double value : sample) {
            sum += value;
        }

        double x1 = Math.pow(sum / sample.size(), 2);

        sum = 0d;
        for (double value : sample) {
            sum += Math.pow(value, 2);
        }

        double x0 = sum / sample.size();
        return x0 - x1;
    }

    public double getFirstPartSampleSize(double dispersion, double quantile, double accuracy) {
        return (Math.pow(quantile, 2) * dispersion) / Math.pow(accuracy, 2);
    }

    public double getSecondPartSampleSize(double quantile, double dispersionProximity) {
        return 1d + (2 * Math.pow(quantile, 2)) / Math.pow(dispersionProximity, 2);
    }
}
