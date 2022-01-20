package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.CardioExercise;
import com.workoutnow.workoutnow.models.UserProfile;
import com.workoutnow.workoutnow.models.data.CardioExerciseGroupRepository;
import com.workoutnow.workoutnow.models.data.CardioExerciseRepository;
import com.workoutnow.workoutnow.models.data.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cardio-exercise")
public class CardioExerciseController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    CardioExerciseRepository cardioExerciseRepository;

    @Autowired
    CardioExerciseGroupRepository cardioExerciseGroupRepository;

    @GetMapping("")
    public String listCardioExercises(Model model, HttpSession session) {
        int currentUserId = (Integer) session.getAttribute("user");
        UserProfile currentUserProfile = userProfileRepository.findByUserId(currentUserId);

        model.addAttribute("exercises", cardioExerciseRepository.findByUserProfiles(currentUserProfile));
        return "cardio-exercise/index";
    }

    @GetMapping("add")
    public String displayAddCardioForm() {

        return "cardio-exercise/add";
    }

    @PostMapping("add")
    public String processAddCardioForm(@RequestParam String name, @RequestParam String equipment, HttpSession session) {
        //need to get and add new exercise
        CardioExercise newExercise = new CardioExercise(name, equipment);

        int currentUserId = (Integer) session.getAttribute("user");
        UserProfile currentUserProfile = userProfileRepository.findByUserId(currentUserId);
        currentUserProfile.addCardioExercise(newExercise);
        // need to add to current user look at lefting exercise controller
        newExercise.addUserProfile(currentUserProfile);
        cardioExerciseRepository.save(newExercise);

        return "redirect:/cardio-exercise/";
    }
}
