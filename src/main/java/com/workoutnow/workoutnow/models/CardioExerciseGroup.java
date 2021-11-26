package com.workoutnow.workoutnow.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CardioExerciseGroup extends AbstractEntity {

    @ManyToOne
    private CardioExercise cardioExercise;

    @OneToOne(cascade = {CascadeType.ALL})
    private RepsAndWeights repsAndWeights;

    @ManyToOne
    private Workout workout;

    private String note;

    public CardioExerciseGroup() {};

    public CardioExerciseGroup(CardioExercise cardioExercise, RepsAndWeights repsAndWeights, String note) {
        this.cardioExercise = cardioExercise;
        this.repsAndWeights = repsAndWeights;
        this.note = note;
    }

    public CardioExerciseGroup(CardioExercise cardioExercise, RepsAndWeights repsAndWeights) {
        this(cardioExercise,repsAndWeights,"");
    }

    public CardioExerciseGroup(CardioExercise cardioExercise) {
        this(cardioExercise,new RepsAndWeights());
    }

    public CardioExercise getCardioExercise() {
        return cardioExercise;
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

    public void setCardioExercise(CardioExercise cardioExercise) {
        this.cardioExercise = cardioExercise;
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
