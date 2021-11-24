package com.workoutnow.workoutnow.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Workout extends AbstractEntity{

    @ManyToOne
    private UserProfile userProfile;

    private final ArrayList<String> exercises = new ArrayList<>();

    private Date dateOfWorkout;

    @ManyToOne
    private Location location;

    public Workout() {};

    public Workout(UserProfile userProfile, Date date, Location location) {
        this.userProfile = userProfile;
        this.location = location;
        this.dateOfWorkout = date;
    };

    public Workout(UserProfile userProfile, Location location) {
        this(userProfile, new Date(), location);
    };

    public UserProfile getUserProfile() {
        return userProfile;
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
