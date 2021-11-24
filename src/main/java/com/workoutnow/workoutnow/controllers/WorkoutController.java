package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.Location;
import com.workoutnow.workoutnow.models.User;
import com.workoutnow.workoutnow.models.Workout;
import com.workoutnow.workoutnow.models.data.LocationRepository;
import com.workoutnow.workoutnow.models.data.UserRepository;
import com.workoutnow.workoutnow.models.data.WorkoutRepository;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("workouts")
public class WorkoutController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("")
    public String listWorkouts (Model model, HttpSession session){
        int currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId);
        model.addAttribute("user", currentUser.getUserProfile());
        model.addAttribute("workouts", workoutRepository.findByUserProfile(currentUser.getUserProfile()));
        return "workouts/index";
    }

    @GetMapping("create")
    public String generateWorkout(Model model, HttpSession session, HttpServletRequest request) {
        int currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId);

        model.addAttribute("locations", currentUser.getUserProfile().getLocations());
        return "workouts/create";
    }

    @PostMapping("create")
    public String processingCreateForm(Model model, @RequestParam Integer locationId, HttpSession session) {


            int currentUserId = (Integer) session.getAttribute("user");
            User currentUser = userRepository.findById(currentUserId);

            Optional<Location> location = locationRepository.findById(locationId);
            if(!location.isPresent()) {
                return "redirect: ";
            }

            Workout currentWorkout = new Workout(currentUser.getUserProfile(), location.get());
            workoutRepository.save(currentWorkout);

        model.addAttribute("user", currentUser.getUserProfile());
        model.addAttribute("workouts", workoutRepository.findByUserProfile(currentUser.getUserProfile()));
        return "workouts/index";

    }


}
