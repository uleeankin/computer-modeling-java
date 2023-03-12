package com.uleeankin.computermodelingjava.utils;

import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChartDataBuilder {

    public Pair<List<Double>, List<Double>> getStatisticalFunctionData(
            ObservableList<Double> items, int sampleSize, int splitSectionNumber) {

        Double[] xData = new Double[splitSectionNumber];
        Double[] yData = new Double[splitSectionNumber];
        double[] occurrencesToSection = new double[splitSectionNumber];
        Arrays.fill(occurrencesToSection, 0);
        Arrays.fill(xData, 0d);
        Arrays.fill(yData, 0d);

        List<Double> randomValues = new ArrayList<>(items);
        Collections.sort(randomValues);
        double endBound = randomValues.get(sampleSize - 1);
        double startBound = randomValues.get(0);

        for (int i = 0; i < splitSectionNumber; i++) {
            double leftBound = i * ((endBound - startBound) / splitSectionNumber) + startBound;
            double rightBound = (i + 1) * ((endBound - startBound) / splitSectionNumber) + startBound;
            for (int j = 0; j < sampleSize; j++) {
                if (randomValues.get(j) > startBound
                        && randomValues.get(j) < rightBound) {
                    occurrencesToSection[i] += 1;
                }
            }
            xData[i] = leftBound;
            yData[i] = occurrencesToSection[i] / sampleSize;
        }

        return new Pair<>(Arrays.asList(xData), Arrays.asList(yData));
    }

    public Pair<List<Double>, List<Double>> getSingleStatisticalFunctionData(
            ObservableList<Double> items, int sampleSize, int splitSectionNumber) {
        Double[] xData = new Double[splitSectionNumber];
        Double[] yData = new Double[splitSectionNumber];
        double[] occurrencesToSection = new double[splitSectionNumber];
        Arrays.fill(occurrencesToSection, 0);
        Arrays.fill(xData, 0d);
        Arrays.fill(yData, 0d);

        for (int i = 0; i < sampleSize; i++) {
            int sectionNumber = ((int)Math.ceil(items.get(i) * splitSectionNumber)) - 1;
            for (int j = sectionNumber; j < splitSectionNumber; j++) {
                occurrencesToSection[j] += 1;
            }
            xData[sectionNumber] = sectionNumber * (1d / splitSectionNumber);
            yData[sectionNumber] = occurrencesToSection[sectionNumber] / sampleSize;
        }

        return new Pair<>(Arrays.asList(xData), Arrays.asList(yData));
    }

    public Pair<List<Double>, List<Double>> getFrequencyHistogramData(
            ObservableList<Double> items, int sampleSize, int splitSectionNumber) {

        Double[] xData = new Double[splitSectionNumber];
        Double[] yData = new Double[splitSectionNumber];
        double[] occurrencesToSection = new double[splitSectionNumber];
        Arrays.fill(occurrencesToSection, 0);
        Arrays.fill(xData, 0d);
        Arrays.fill(yData, 0d);

        List<Double> randomValues = new ArrayList<>(items);
        Collections.sort(randomValues);
        double endBound = randomValues.get(sampleSize - 1);
        double startBound = randomValues.get(0);

        for (int i = 0; i < splitSectionNumber; i++) {
            double leftBound = i * ((endBound - startBound) / splitSectionNumber) + startBound;
            double rightBound = (i + 1) * ((endBound - startBound) / splitSectionNumber) + startBound;
            for (int j = 0; j < sampleSize; j++) {
                if (randomValues.get(j) > leftBound
                    && randomValues.get(j) < rightBound) {
                    occurrencesToSection[i] += 1;
                }
            }
            xData[i] = leftBound;
            yData[i] = occurrencesToSection[i] / sampleSize;
        }

        return new Pair<>(Arrays.asList(xData), Arrays.asList(yData));
    }

    public Pair<List<Double>, List<Double>> getSingleFrequencyHistogramData(
            ObservableList<Double> items, int sampleSize, int splitSectionNumber) {
        return new Pair<>(getFrequencyHistogramXData(splitSectionNumber),
                getFrequencyHistogramYData(items, sampleSize, splitSectionNumber));
    }

    private List<Double> getFrequencyHistogramXData(int splitSectionNumber) {
        List<Double> xData = new ArrayList<>();
        for (int i = 0; i < splitSectionNumber; i++) {
            xData.add(i * (1.0 / (double) splitSectionNumber));
        }
        return xData;
    }

    private List<Double> getFrequencyHistogramYData(
            ObservableList<Double> items, int sampleSize, int splitSectionNumber) {

        Double[] yData = new Double[splitSectionNumber];
        double[] occurrencesToSection = new double[splitSectionNumber];
        Arrays.fill(occurrencesToSection, 0);
        Arrays.fill(yData, 0d);

        for (int i = 0; i < sampleSize; i++) {
            int sectionNumber = ((int)Math.ceil(items.get(i) * splitSectionNumber)) - 1;
            occurrencesToSection[sectionNumber] += 1;
            yData[sectionNumber] = occurrencesToSection[sectionNumber]
                    / (sampleSize  * (1d / splitSectionNumber));
        }

        return Arrays.asList(yData);
    }
}
