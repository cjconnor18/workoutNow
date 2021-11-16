package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.User;
import com.workoutnow.workoutnow.models.Workout;
import com.workoutnow.workoutnow.models.data.UserRepository;
import com.workoutnow.workoutnow.models.data.WorkoutRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("workouts")
public class WorkoutController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @GetMapping("")
    public String listWorkouts (Model model, HttpSession session){
        int currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId);
        model.addAttribute("user", currentUser);
        model.addAttribute("workouts", workoutRepository.findByUser(currentUser));
        return "workouts/index";
    }

    @GetMapping("create")
    public String generateWorkout(Model model, HttpSession session, HttpServletRequest request) {
//        int currentUserId = (Integer) session.getAttribute("user");
//        User currentUser = userRepository.findById(currentUserId);
//        Workout workout = new Workout(currentUser);
//        model.addAttribute(workout);
//        session.getAttribute("user");
        // model.addAttribute("user", session.getAttribute(request.getSession().getId()));
//        model.addAttribute("user", session.getAttribute("user"));
        return "workouts/create";
    }

    @PostMapping("create")
    public String processingCreateForm(@RequestParam String location, HttpSession session) {

        if(location.length() > 0) {
            int currentUserId = (Integer) session.getAttribute("user");
            User currentUser = userRepository.findById(currentUserId);

            Workout currentWorkout = new Workout(currentUser, location);
            workoutRepository.save(currentWorkout);
        }

        return "redirect:";
    }
}
