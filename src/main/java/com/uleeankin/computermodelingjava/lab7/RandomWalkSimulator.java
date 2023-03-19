package com.uleeankin.computermodelingjava.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class RandomWalkSimulator {

    public ObservableList<Double> getRandomWalksTime(int testNumber,
                                                           double leftScreenPosition,
                                                           double rightScreenPosition,
                                                           double initialPosition,
                                                           double rightStepProbability) {

        ObservableList<Double> randomWalk = FXCollections.observableArrayList();
        Random random = new Random();

        for (int i = 0; i < testNumber; i++) {
            double x = initialPosition;
            int time = 0;
            while (x != leftScreenPosition
                    && x != rightScreenPosition) {

                double r = random.nextDouble();
                if (r < rightStepProbability) {
                    x++;
                } else {
                    x--;
                }
                time++;
            }
            randomWalk.add((double)time);
        }
        return randomWalk;
    }

    public double getAverageWalkTime(ObservableList<Double> randomWalks) {

        return randomWalks.stream().mapToDouble(Double::doubleValue).sum()
                / randomWalks.size();

    }
}
