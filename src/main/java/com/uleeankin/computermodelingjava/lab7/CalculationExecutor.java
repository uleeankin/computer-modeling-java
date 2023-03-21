package com.uleeankin.computermodelingjava.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class CalculationExecutor {

    private final List<RandomWalkRunner> tasks;
    private final ExecutorService executor;

    public CalculationExecutor(ExecutorService executor,
                               int experimentsNumber,
                               int testNumber,
                               int threadsNumber,
                               double leftScreenPosition,
                               double rightScreenPosition,
                               double initialPosition,
                               double rightStepProbability) {

        this.tasks = new TaskService().getTasks(experimentsNumber,
                testNumber, leftScreenPosition, rightScreenPosition,
                initialPosition, rightStepProbability,
                new Semaphore(threadsNumber / 2, true));
        this.executor = executor;
    }

    public ObservableList<Double> execute()
            throws ExecutionException, InterruptedException {
        List<Future<Double>> futures = new ArrayList<>();
        for (RandomWalkRunner task : this.tasks) {
            futures.add(this.executor.submit(task));
        }

        ObservableList<Double> result = this.getCalculationResult(futures);

        this.executor.shutdown();

        return result;
    }

    private ObservableList<Double> getCalculationResult(List<Future<Double>> futures)
            throws ExecutionException, InterruptedException {
        ObservableList<Double> result = FXCollections.observableArrayList();
        for (Future<Double> future : futures) {
            result.add(future.get());
        }
        return result;
    }
}
