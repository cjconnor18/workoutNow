package com.workoutnow.workoutnow.models.data;


import com.workoutnow.workoutnow.models.UserProfile;
import com.workoutnow.workoutnow.models.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WorkoutRepository extends CrudRepository<Workout,Integer> {

    List<Workout> findByUserProfile(UserProfile userProfile);


}
