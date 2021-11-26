package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.*;
import com.workoutnow.workoutnow.models.data.*;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("workouts")
public class WorkoutController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LiftingExerciseGroupRepository liftingExerciseGroupRepository;

    @Autowired
    LiftingExerciseRepository liftingExerciseRepository;

    @Autowired
    RepsAndWeightsRepository repsAndWeightsRepository;

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

    @GetMapping("display/{workoutId}")
    public String displayWorkout(@PathVariable Integer workoutId, Model model) {
       Optional<Workout> workoutOpt = workoutRepository.findById(workoutId);
       if(!workoutOpt.isPresent()) {
           return "redirect:";
       }

       model.addAttribute("workout", workoutOpt.get());
       model.addAttribute("liftings", workoutOpt.get().getExercises());

       return "workouts/display";
    }

    @GetMapping("display/{workoutId}/addLiftingGroupExercise")
    public String addLiftingGroupExerciseToWorkout(Model model, HttpSession session) {

        int currentUserId = (Integer) session.getAttribute("user");
        UserProfile currentProfile = userProfileRepository.findByUserId(currentUserId);

        model.addAttribute("exercises", liftingExerciseRepository.findByUserProfiles(currentProfile));

        return "workouts/addLiftingGroupExercise";
    }

    @PostMapping("display/{workoutId}/addLiftingGroupExercise")
    public String processAddLiftingGroupExerciseToWorkout(@PathVariable Integer workoutId, @RequestParam Integer exerciseId, HttpSession session) {

        int currentUserId = (Integer) session.getAttribute("user");
        UserProfile currentProfile = userProfileRepository.findByUserId(currentUserId);

        Optional<LiftingExercise> currentExercise = liftingExerciseRepository.findById(exerciseId);
        Optional<Workout> currentWorkout = workoutRepository.findById(workoutId);
        if(!currentExercise.isPresent() || !currentWorkout.isPresent()) {
            return "redirect:";
        }


        LiftingExerciseGroup newExerciseGroup = new LiftingExerciseGroup(currentExercise.get());
        newExerciseGroup.setWorkout(currentWorkout.get());
        liftingExerciseGroupRepository.save(newExerciseGroup);

        return "redirect:/workouts/display/" + workoutId;
    }

    @GetMapping("display/{workoutId}/{exerciseId}/addSets")
    public String addSets(@PathVariable Integer workoutId, @PathVariable Integer exerciseId, Model model) {
        Optional<LiftingExerciseGroup> groupOpt = liftingExerciseGroupRepository.findById(exerciseId);
        if(!groupOpt.isPresent()) {
            return "/workouts/display/" + workoutId;
        }

        Optional<RepsAndWeights> currentReps = repsAndWeightsRepository.findById(groupOpt.get().getRepsAndWeights().getId());
        if(!currentReps.isPresent()) {
            return "redirect:/workouts/display/" + workoutId;
        }
        model.addAttribute("workoutId", workoutId);
        model.addAttribute("exerciseId", exerciseId);
        model.addAttribute("exerciseGroup", groupOpt.get());
        model.addAttribute("reps", currentReps.get().getRepsList());
        model.addAttribute("weights", currentReps.get().getWeightsList());
        return "workouts/addSets";
    }

    @PostMapping("display/{workoutId}/{exerciseId}/addSets")
    public String addSets(@PathVariable Integer workoutId, @PathVariable Integer exerciseId, @RequestParam(defaultValue = "-1.0") Double weight, @RequestParam Double reps) {

        Optional<LiftingExerciseGroup> groupOpt = liftingExerciseGroupRepository.findById(exerciseId);
        if(!groupOpt.isPresent() || weight < 0) {
            return "redirect:/workouts/display/" + workoutId;
        }

        groupOpt.get().getRepsAndWeights().addSet(weight, reps);


        //currentReps.get().addSet(weight, reps);
        repsAndWeightsRepository.save(groupOpt.get().getRepsAndWeights());

        return "redirect:/workouts/display/" + workoutId + "/" + exerciseId + "/addSets";
    }

    @GetMapping("display/{workoutId}/{exerciseId}/deleteSet/{index}")
    public String deleteSet(@PathVariable Integer workoutId, @PathVariable Integer exerciseId, @PathVariable Integer index){
        Optional<LiftingExerciseGroup> groupOpt = liftingExerciseGroupRepository.findById(exerciseId);
        if(!groupOpt.isPresent()) {
            return "redirect:/workouts/display/" + workoutId + "/" + exerciseId + "/addSets";
        }

        RepsAndWeights repsAndWeights = groupOpt.get().getRepsAndWeights();
        repsAndWeights.deleteSet(index);

        repsAndWeightsRepository.save(repsAndWeights);

        return "redirect:/workouts/display/" + workoutId + "/" + exerciseId + "/addSets";

    }

}
