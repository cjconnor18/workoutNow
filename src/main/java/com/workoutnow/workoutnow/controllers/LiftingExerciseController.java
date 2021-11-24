package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.LiftingExercise;
import com.workoutnow.workoutnow.models.User;
import com.workoutnow.workoutnow.models.UserProfile;
import com.workoutnow.workoutnow.models.data.LiftingExerciseRepository;
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
@RequestMapping("lifting-exercise")
public class LiftingExerciseController {

    @Autowired
    LiftingExerciseRepository liftingExerciseRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String index(Model model, HttpSession session) {
        int currentUserId = (Integer) session.getAttribute("user");
        UserProfile currentUserProfile = userProfileRepository.findByUserId(currentUserId);

        List<LiftingExercise> currentExercises = liftingExerciseRepository.findByUserProfiles(currentUserProfile);
        model.addAttribute("exercises", currentExercises);

        return "lifting-exercise/index";
    }

    @GetMapping("add")
    public String createNewLiftingExercise(Model model) {

        return "lifting-exercise/add";
    }

    @PostMapping("add")
    public String processCreateNewLiftingExercise(@RequestParam String name, @RequestParam String equipment, HttpSession session) {
        if(name.isEmpty() || name.isBlank() || equipment.isBlank() || equipment.isEmpty()) {
            return "redirect:";
        }
        int currentUserId = (Integer) session.getAttribute("user");
        UserProfile currentUserProfile = userProfileRepository.findByUserId(currentUserId);

        LiftingExercise newExercise = new LiftingExercise(name, equipment);
        currentUserProfile.addLiftingExercise(newExercise);
        newExercise.addUserProfile(currentUserProfile);
        liftingExerciseRepository.save(newExercise);

        return "redirect:";
    }



}
