package com.workoutnow.workoutnow.models.dto;

import com.workoutnow.workoutnow.models.Location;
import com.workoutnow.workoutnow.models.User;

public class UserLocationDTO {

    private User user;
    private Location location;

    public UserLocationDTO() {};

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
