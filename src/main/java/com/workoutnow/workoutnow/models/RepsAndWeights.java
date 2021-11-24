package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RepsAndWeights extends AbstractEntity {

    private final ArrayList<Integer> reps = new ArrayList<>();
    private final ArrayList<Double> weights = new ArrayList<>();

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
