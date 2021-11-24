package com.workoutnow.workoutnow.models.data;

import com.workoutnow.workoutnow.models.LiftingExercise;
import com.workoutnow.workoutnow.models.UserProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LiftingExerciseRepository extends CrudRepository<LiftingExercise, Integer> {

    public List<LiftingExercise> findByUserProfiles(UserProfile userProfile);

}
