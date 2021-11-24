package com.workoutnow.workoutnow.models.data;

import com.workoutnow.workoutnow.models.Location;
import com.workoutnow.workoutnow.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    List<Location> findByUsers(User user);
}
