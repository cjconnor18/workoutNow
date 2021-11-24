package com.workoutnow.workoutnow.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Workout extends AbstractEntity{

    @ManyToOne
    private User user;

    private final ArrayList<String> exercises = new ArrayList<>();

    private Date dateOfWorkout;

    @ManyToOne
    private Location location;

    public Workout() {};

    public Workout(User user, Date date, Location location) {
        this.user = user;
        this.location = location;
        this.dateOfWorkout = date;
    };

    public Workout(User user, Location location) {
        this(user, new Date(), location);
    };

    public User getUser() {
        return user;
    }


    public ArrayList<String> getExercises() {
        return exercises;
    }

    public Date getDateOfWorkout() {
        return dateOfWorkout;
    }

    public void setDateOfWorkout(Date dateOfWorkout) {
        this.dateOfWorkout = dateOfWorkout;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addExercise(String anExercise) {
        this.exercises.add(anExercise);
    }

    public void removeExercise(String anExercise) {
        if(this.exercises.contains(anExercise)){
            exercises.remove(anExercise);
        }
    }
}
