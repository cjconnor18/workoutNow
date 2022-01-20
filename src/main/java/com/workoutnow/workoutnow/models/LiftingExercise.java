package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LiftingExercise extends AbstractEntity{

    private String name;

    private String equipment;

    @OneToMany(mappedBy = "liftingExercise")
    private List<LiftingExerciseGroup> LiftingExerciseGroup = new ArrayList<>();

    @ManyToMany
    private final List<UserProfile> userProfiles = new ArrayList<>();

    public LiftingExercise() {};

    public LiftingExercise(String name, String equipment) {
        this.name = name;
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public List<com.workoutnow.workoutnow.models.LiftingExerciseGroup> getLiftingExerciseGroup() {
        return LiftingExerciseGroup;
    }

    public void addUserProfile(UserProfile userProfile) {
        this.userProfiles.add(userProfile);
    }
}
