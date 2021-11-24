package com.workoutnow.workoutnow.models;

import org.hibernate.jdbc.Work;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserProfile extends AbstractEntity {

    @OneToOne
    private User user;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToMany
    private final List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    private final List<Workout> workouts = new ArrayList<>();

    public UserProfile() {};

    public UserProfile(User user, String firstName, String lastName, String email) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    public List<Workout> getWorkouts() {
        return this.workouts;
    }

    public List<Location> getLocations() {return this.locations; }

    public void addLocation(Location location) {this.locations.add(location); }

}
