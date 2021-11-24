package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.Location;
import com.workoutnow.workoutnow.models.User;
import com.workoutnow.workoutnow.models.data.LocationRepository;
import com.workoutnow.workoutnow.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("add")
    public String createAddLocation() {

        return "location/add";
    }

    @PostMapping("add")
    public String processAddLocation(@RequestParam String facilityName, @RequestParam String city, @RequestParam String state, HttpSession session){
        if(facilityName.isEmpty() || city.isEmpty() || state.isEmpty()) {
            return "redirect:";
        }
        int currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId);

        Location newLocation = new Location(facilityName, city, state);
        currentUser.getUserProfile().addLocation(newLocation);
        newLocation.addUser(currentUser.getUserProfile());
        locationRepository.save(newLocation);



        return "redirect:/workouts/create";
    }

}
