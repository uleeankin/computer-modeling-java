package com.uleeankin.computermodelingjava.lab6;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MonteCarloModulator {

    public ObservableList<ExperimentResult> startExperiment(
            int sampleSize, ObservableList<ExperimentResult> result) {
        List<Double> probabilities = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < sampleSize; i++) {
            for (int j = 0; j < 10; j++) {
                probabilities.add(random.nextDouble());
            }

            result.add(addExperiment(probabilities));
            probabilities.clear();
        }
        return result;
    }

    public double[] countProbability(int sampleSize, double probability,
                                  ObservableList<ExperimentResult> result) {
        int[] n = new int[4];
        Arrays.fill(n, 0);

        for (int i = 0; i < sampleSize; i++) {
            int rejections = getRejections(result.get(i), probability);
            if (rejections > 0) {
                n[0]++;
            }
            if (rejections == 1) {
                n[1]++;
            }
            if (rejections == 2) {
                n[2]++;
            }
            if (rejections >= 2) {
                n[3]++;
            }
        }

        double[] p = new double[4];

        for (int i = 0; i < 4; i++) {
            p[i] = ((double) n[i] / sampleSize);
        }
        return p;
    }

    private int getRejections(ExperimentResult experimentResult, double probability) {
        int rejections = 0;
        if (experimentResult.getP1() < probability) {
            rejections++;
        }
        if (experimentResult.getP2() < probability) {
            rejections++;
        }
        if (experimentResult.getP3() < probability) {
            rejections++;
        }
        if (experimentResult.getP4() < probability) {
            rejections++;
        }
        if (experimentResult.getP5() < probability) {
            rejections++;
        }
        if (experimentResult.getP6() < probability) {
            rejections++;
        }
        if (experimentResult.getP7() < probability) {
            rejections++;
        }
        if (experimentResult.getP8() < probability) {
            rejections++;
        }
        if (experimentResult.getP9() < probability) {
            rejections++;
        }
        if (experimentResult.getP10() < probability) {
            rejections++;
        }

        return rejections;
    }

    private ExperimentResult addExperiment(List<Double> probabilities) {

        return new ExperimentResult(probabilities.get(0),
                probabilities.get(1),
                probabilities.get(2),
                probabilities.get(3),
                probabilities.get(4),
                probabilities.get(5),
                probabilities.get(6),
                probabilities.get(7),
                probabilities.get(8),
                probabilities.get(9));
    }
}
