package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LiftingExerciseGroup extends AbstractEntity {

    @ManyToOne
    private LiftingExercise liftingExercise;

    @OneToOne
    private RepsAndWeights repsAndWeights;

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

    public LiftingExercise getLiftingExercise() {
        return liftingExercise;
    }

    public RepsAndWeights getRepsAndWeights() {
        return repsAndWeights;
    }

    public String getNote() {
        return note;
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

}
