package com.workoutnow.workoutnow.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Workout extends AbstractEntity{

    @ManyToOne
    private UserProfile userProfile;

    @OneToMany(mappedBy = "workout")
    private final List<LiftingExerciseGroup> liftingExerciseGroups = new ArrayList<>();

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

    public List<LiftingExerciseGroup> getExercises() {
        return this.liftingExerciseGroups;
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

    public void addExercise(LiftingExerciseGroup liftingExerciseGroup) {
        this.liftingExerciseGroups.add(liftingExerciseGroup);
    }

    public void removeExercise(LiftingExerciseGroup liftingExerciseGroup) {
        if(this.liftingExerciseGroups.contains(liftingExerciseGroup)){
            this.liftingExerciseGroups.remove(liftingExerciseGroup);
        }
    }
}
