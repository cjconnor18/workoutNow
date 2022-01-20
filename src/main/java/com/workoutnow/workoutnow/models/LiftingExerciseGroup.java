package com.workoutnow.workoutnow.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LiftingExerciseGroup extends AbstractEntity {

    @ManyToOne
    private LiftingExercise liftingExercise;

    @OneToOne(cascade = {CascadeType.ALL})
    private RepsAndWeights repsAndWeights;

    @ManyToOne
    private Workout workout;

    private String note;

    public LiftingExerciseGroup() {};

    public LiftingExerciseGroup(LiftingExercise liftingExercise, RepsAndWeights repsAndWeights, String note) {
        this.liftingExercise = liftingExercise;
        this.repsAndWeights = repsAndWeights;
        this.note = note;
    }

    public LiftingExerciseGroup(LiftingExercise liftingExercise, RepsAndWeights repsAndWeights) {
        this(liftingExercise,repsAndWeights,"");
    }

    public LiftingExerciseGroup(LiftingExercise liftingExercise) {
        this(liftingExercise,new RepsAndWeights());
    }

    public LiftingExercise getLiftingExercise() {
        return liftingExercise;
    }

    public RepsAndWeights getRepsAndWeights() {
        return repsAndWeights;
    }

    public String getNote() {
        return note;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setLiftingExercise(LiftingExercise liftingExercise) {
        this.liftingExercise = liftingExercise;
    }

    public void setRepsAndWeights(RepsAndWeights repsAndWeights) {
        this.repsAndWeights = repsAndWeights;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
