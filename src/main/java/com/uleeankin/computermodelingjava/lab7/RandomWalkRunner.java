package com.uleeankin.computermodelingjava.lab7;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class RandomWalkRunner implements Callable<Double> {

    private final RandomWalkSimulator simulator;
    private final int testNumber;
    private final double leftScreenPosition;
    private final double rightScreenPosition;
    private final double initialPosition;
    private final double rightStepProbability;
    private final Semaphore semaphore;

    public RandomWalkRunner(RandomWalkSimulator simulator,
                            int testNumber,
                            double leftScreenPosition,
                            double rightScreenPosition,
                            double initialPosition,
                            double rightStepProbability,
                            Semaphore semaphore) {

        this.simulator = simulator;
        this.testNumber = testNumber;
        this.leftScreenPosition = leftScreenPosition;
        this.rightScreenPosition = rightScreenPosition;
        this.rightStepProbability = rightStepProbability;
        this.initialPosition = initialPosition;
        this.semaphore = semaphore;
    }

    @Override
    public Double call() {

        double averageTime = 0d;
        try {
            this.semaphore.acquire();
            averageTime = this.simulator.getAverageWalkTime(
                    this.simulator.getRandomWalksTime(
                            this.testNumber,
                            this.leftScreenPosition,
                            this.rightScreenPosition,
                            this.initialPosition,
                            this.rightStepProbability));
            this.semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return averageTime;
    }
}
