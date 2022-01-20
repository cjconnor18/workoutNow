package com.workoutnow.workoutnow.models.dto;

import java.util.List;

public class LocationAddFormDTO {

    public List<Integer> locations;

    public LocationAddFormDTO() {};

    public List<Integer> getLocations() {
        return locations;
    }

    public void setLocations(List<Integer> locations) {
        this.locations = locations;
    }
}
