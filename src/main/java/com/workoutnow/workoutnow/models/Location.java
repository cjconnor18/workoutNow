package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location extends AbstractEntity{

    private String city;
    private String state;
    private String facilityName;

    @OneToMany(mappedBy = "location")
    private final List<Workout> workouts = new ArrayList<>();

    @ManyToMany(mappedBy = "locations")
    private final List<User> users = new ArrayList<>();




    public Location() {
    }

    public Location(String facilityName, String city, String state) {
        this.city = city;
        this.state = state;
        this.facilityName = facilityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
