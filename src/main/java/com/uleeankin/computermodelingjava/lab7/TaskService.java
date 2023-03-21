package com.uleeankin.computermodelingjava.lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TaskService {

    public TaskService() {

    }

    public List<RandomWalkRunner> getTasks(int experimentsNumber,
                                           int testNumber,
                                           double leftScreenPosition,
                                           double rightScreenPosition,
                                           double initialPosition,
                                           double rightStepProbability,
                                           Semaphore semaphore) {

        List<RandomWalkRunner> tasks = new ArrayList<>();

        for (int i = 0; i < experimentsNumber; i++) {
            tasks.add(new RandomWalkRunner(new RandomWalkSimulator(),
                    testNumber, leftScreenPosition, rightScreenPosition,
                    initialPosition, rightStepProbability, semaphore));
        }

        return tasks;
    }
}
