package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.Duration;


@Entity
public class DistanceAndTime extends AbstractEntity {

    private Double distanceMiles = 0.0;
    private Double timeMin = 0.0;
    private Double timeSeconds =0.0;

    public DistanceAndTime() {};

    public Double getDistanceMiles() {
        return distanceMiles;
    }

    public Double getTimeMin() {
        return timeMin;
    }

    public Double getTimeSeconds() {
        return timeSeconds;
    }

    public void setDistanceMiles(Double distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public void setTimeMin(Double timeMin) {
        this.timeMin = timeMin;
    }

    public void setTimeSeconds(Double timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
}
