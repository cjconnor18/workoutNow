package com.workoutnow.workoutnow.models;

import com.workoutnow.workoutnow.controllers.AuthenticationController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToMany(mappedBy = "user")
    private final List<Workout> workouts = new ArrayList<>();

    @ManyToMany
    private final List<Location> locations = new ArrayList<>();

    public User() {};

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
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