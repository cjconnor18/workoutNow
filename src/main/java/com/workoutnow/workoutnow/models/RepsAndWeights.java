package com.workoutnow.workoutnow.models;

import java.util.ArrayList;
import java.util.List;

public class RepsAndWeights {
    private final List<Integer> reps = new ArrayList<>();
    private final List<Double> weights = new ArrayList<>();

    public RepsAndWeights() {};

    public List<Double> getWeights() {
        return weights;
    }

    public List<Integer> getReps() {
        return reps;
    }

    public void addSet(Double aWeight, Integer aReps) {
        this.reps.add(aReps);
        this.weights.add(aWeight);
    }
}
