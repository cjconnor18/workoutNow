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
    private DistanceAndTime distanceAndTime;

    @ManyToOne
    private Workout workout;

    private String note;

    public CardioExerciseGroup() {};

    public CardioExerciseGroup(CardioExercise cardioExercise, DistanceAndTime distanceAndTime, String note) {
        this.cardioExercise = cardioExercise;
        this.distanceAndTime = distanceAndTime;
        this.note = note;
    }

    public CardioExerciseGroup(CardioExercise cardioExercise, DistanceAndTime distanceAndTime) {
        this(cardioExercise,distanceAndTime,"");
    }

    public CardioExerciseGroup(CardioExercise cardioExercise) {
        this(cardioExercise,new DistanceAndTime());
    }

    public CardioExercise getCardioExercise() {
        return cardioExercise;
    }

    public DistanceAndTime getDistanceAndTime() {
        return distanceAndTime;
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

    public void setDistanceAndTime(DistanceAndTime distanceAndTime) {
        this.distanceAndTime = distanceAndTime;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
