package com.workoutnow.workoutnow.models.data;


import com.workoutnow.workoutnow.models.User;
import com.workoutnow.workoutnow.models.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository  extends CrudRepository<UserProfile, Integer> {

    UserProfile findByUser(User user);

}
