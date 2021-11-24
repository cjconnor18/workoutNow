package com.workoutnow.workoutnow.models.data;

import com.workoutnow.workoutnow.models.Location;
import com.workoutnow.workoutnow.models.UserProfile;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    List<Location> findByUserProfiles(UserProfile userProfiles);
}
