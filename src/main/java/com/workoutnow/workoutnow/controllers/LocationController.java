package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.Location;
import com.workoutnow.workoutnow.models.User;
import com.workoutnow.workoutnow.models.UserProfile;
import com.workoutnow.workoutnow.models.data.LocationRepository;
import com.workoutnow.workoutnow.models.data.UserProfileRepository;
import com.workoutnow.workoutnow.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("add")
    public String createAddLocation(Model model, HttpSession session) {
        int currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId);
        UserProfile currentProfile = userProfileRepository.findByUserId(currentUserId);
        List<Location> locations = locationRepository.findByUserProfilesNot(currentProfile);
        List<Location> currentLocations = locationRepository.findByUserProfiles(currentProfile);
        for (Location location : currentLocations) {
            locations.removeIf(location2 -> location2.equals(location));
        }
        model.addAttribute("locations", locations);
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

    @PostMapping("addMultiples")
    public String processAddLocation(@RequestParam List<Integer> addLocations, HttpSession session) {
        int currentUserId = (Integer) session.getAttribute("user");
        UserProfile currentUser = userProfileRepository.findByUserId(currentUserId);
        for(Integer aLocation : addLocations) {
            Optional<Location> currentLocation = locationRepository.findById(aLocation);
            if(currentLocation.isPresent()) {
                currentUser.addLocation(currentLocation.get());
                locationRepository.save(currentLocation.get());
            }
        }
        userProfileRepository.save(currentUser);

        return "redirect:/workouts/create";
    }

}
