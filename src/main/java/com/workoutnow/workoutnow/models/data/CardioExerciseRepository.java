package com.workoutnow.workoutnow.models.data;

import com.workoutnow.workoutnow.models.CardioExercise;
import com.workoutnow.workoutnow.models.LiftingExercise;
import com.workoutnow.workoutnow.models.UserProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardioExerciseRepository extends CrudRepository<CardioExercise, Integer> {

    public List<CardioExercise> findByUserProfiles(UserProfile userProfile);

}
