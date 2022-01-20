package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CardioExercise extends AbstractEntity{

    private String name;

    private String equipment;

    @OneToMany(mappedBy = "cardioExercise")
    private List<CardioExerciseGroup> CardioExerciseGroup = new ArrayList<>();

    @ManyToMany
    private final List<UserProfile> userProfiles = new ArrayList<>();

    public CardioExercise() {};

    public CardioExercise(String name, String equipment) {
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

    public List<CardioExerciseGroup> getCardioExerciseGroup() {
        return CardioExerciseGroup;
    }

    public void addUserProfile(UserProfile userProfile) {
        this.userProfiles.add(userProfile);
    }
}
