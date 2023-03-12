package com.uleeankin.computermodelingjava.utils;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.util.Pair;

import java.util.List;

public class ControlsDataDisplayer {

    public static void buildFrequencyHistogram(ObservableList<Double> items,
                                         int splitSectionNumber,
                                         int sampleSize, Group group,
                                               boolean single, String title) {
        ChartDataBuilder chartDataBuilder = new ChartDataBuilder();
        Pair<List<Double>, List<Double>> frequencyHistogramData;

        if (single) {
            frequencyHistogramData =
                    chartDataBuilder.getSingleFrequencyHistogramData(
                            items, sampleSize, splitSectionNumber);
        } else {
            frequencyHistogramData =
                    chartDataBuilder.getFrequencyHistogramData(
                            items, sampleSize, splitSectionNumber);
        }

        buildBarChart(frequencyHistogramData, group, splitSectionNumber, title);
    }

    public static void buildStatisticalFunction(ObservableList<Double> items,
                                          int splitSectionNumber,
                                          int sampleSize, Group group,
                                                boolean single, String title) {

        ChartDataBuilder chartDataBuilder = new ChartDataBuilder();
        Pair<List<Double>, List<Double>> statisticalFunctionData;

        if (single) {
            statisticalFunctionData =
                    chartDataBuilder.getSingleStatisticalFunctionData(
                            items, sampleSize, splitSectionNumber);
        } else {
            statisticalFunctionData =
                    chartDataBuilder.getStatisticalFunctionData(
                            items, sampleSize, splitSectionNumber);
        }


        buildBarChart(statisticalFunctionData, group, splitSectionNumber, title);
    }

    private static void buildBarChart(Pair<List<Double>, List<Double>> histogramData,
                               Group group, int splitSectionNumber, String title) {
        group.getChildren().clear();
        BarChart<String, Number> chart
                = new BarChart<>(new CategoryAxis(), new NumberAxis());
        chart.setLegendVisible(false);

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

        for (int i = 0; i < splitSectionNumber; i++) {
            dataSeries.getData().add(
                    new XYChart.Data<>(
                            String.format("%.2f", histogramData.getKey().get(i)),
                            histogramData.getValue().get(i)));
        }

        chart.getData().add(dataSeries);
        chart.setTitle(title);
        group.getChildren().add(chart);
    }

    public static void showRandomValues(ObservableList<Double> items,
                                  ListView<Double> listView) {
        listView.getItems().clear();
        listView.getItems().addAll(items);
    }
}
