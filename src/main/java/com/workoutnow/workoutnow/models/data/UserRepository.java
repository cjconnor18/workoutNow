package com.workoutnow.workoutnow.models.data;

import com.workoutnow.workoutnow.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
