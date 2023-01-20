package com.tambadenis.WorkoutTracker.controller;

import com.tambadenis.WorkoutTracker.model.Workout;
import com.tambadenis.WorkoutTracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/workouts")
    public String getAllWorkouts(Model model) {
        List<Workout> workouts = workoutService.getAllWorkouts();
        model.addAttribute("workouts", workouts);
        return "workouts";
    }

    @GetMapping("/workouts/{id}")
    public String getWorkoutById(@PathVariable("id") Long id, Model model) {
        Workout workout = workoutService.getWorkoutById(id);
        model.addAttribute("workout", workout);
        return "workout-details";
    }

    @GetMapping("/workouts/add")
    public String showAddWorkoutForm(Workout workout) {
        return "add-workout";
    }

    @PostMapping("/workouts/add")
    public String addWorkout(Workout workout, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add-workout";
        }
        workoutService.saveWorkout(workout);
        redirectAttributes.addFlashAttribute("workouts", workoutService.getAllWorkouts());
        return "redirect:/workouts";
    }

    @GetMapping("/workouts/edit/{id}")
    public String showUpdateWorkoutForm(@PathVariable("id") Long id, Model model) {
        Workout workout = workoutService.getWorkoutById(id);
        model.addAttribute("workout", workout);
        return "update-workout";
    }

    @PostMapping("/workouts/update/{id}")
    public String updateWorkout(@PathVariable("id") Long id, Workout workout, BindingResult result,
                                Model model) {
        workoutService.updateWorkout(id, workout);
        model.addAttribute("workouts", workoutService.getAllWorkouts());
        return "workouts";
    }

    @GetMapping("/workouts/delete/{id}")
    public String deleteWorkout(@PathVariable("id") Long id, Model model) {
        Workout workout = workoutService.getWorkoutById(id);
        workoutService.deleteWorkout(id);
        model.addAttribute("workouts", workoutService.getAllWorkouts());
        return "workouts";
    }
}