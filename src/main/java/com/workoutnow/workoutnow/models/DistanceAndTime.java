package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.time.Duration;


@Entity
public class DistanceAndTime extends AbstractEntity {

    private Double distanceMiles = 0.0;
    private Integer timeMin = 0;
    private Double timeSeconds =0.0;

    public DistanceAndTime() {};

    public Double getDistanceMiles() {
        return round(distanceMiles);
    }

    public Double getDistanceKM() {
        return round(this.distanceMiles * 1.60934);
    }

    public Double getDistanceMeters() {
        return round(this.distanceMiles * 1609.34);
    }

    public Integer getTimeMin() {
        return timeMin;
    }

    public Double getTimeSeconds() {
        return timeSeconds;
    }


    public void setDistanceMiles(Double distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public void setDistanceKM(Double km) {
        this.distanceMiles = km / 1.60934;
    }

    public  void setDistanceMeters(Double meters) {
        this.distanceMiles = meters / 1609.34;
    }

    public void setTimeMin(Integer timeMin) {
        this.timeMin = timeMin;
    }

    public void setTimeSeconds(Double timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    private Double round(Double num) {
        int places = 2;

        BigDecimal bd = BigDecimal.valueOf(num);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
