package com.workoutnow.workoutnow.models.data;

import com.workoutnow.workoutnow.models.CardioExerciseGroup;
import com.workoutnow.workoutnow.models.LiftingExerciseGroup;
import org.springframework.data.repository.CrudRepository;

public interface CardioExerciseGroupRepository extends CrudRepository<CardioExerciseGroup, Integer> {
}
