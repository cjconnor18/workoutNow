package com.workoutnow.workoutnow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cardio-exercise")
public class CardioExerciseController {

    @GetMapping("")
    public String listCardioExercises(Model model) {
        //need to add exercises attribute
        return "cardio-exercise/index";
    }

    @GetMapping("add")
    public String displayAddCardioForm() {

        return "cardio-exercise/add";
    }

    @PostMapping("add")
    public String processAddCardioForm() {
        //need to get and add new exercise
        return "redirect:/cardio-exercise/";
    }
}
