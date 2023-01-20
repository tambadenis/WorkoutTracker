package com.tambadenis.WorkoutTracker.controller;

import com.tambadenis.WorkoutTracker.model.Set;
import com.tambadenis.WorkoutTracker.model.Workout;
import com.tambadenis.WorkoutTracker.repository.SetRepository;
import com.tambadenis.WorkoutTracker.repository.WorkoutRepository;
import com.tambadenis.WorkoutTracker.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SetController {
    @Autowired
    private SetService setService;
    @Autowired
    private SetRepository setRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @GetMapping("/workouts/sets")
    public String getAllSets(Model model) {
        List<Set> sets = setService.getAllSets();
        model.addAttribute("set", new Set());
        return "sets";
    }

//    @GetMapping("/workouts/sets/{id}")
//    public String getSetById(@PathVariable("id") Long id, Model model) {
//        Set set = setService.getSetsById(id);
//        model.addAttribute("set", new Set());
//        return "sets-details";
//    }

    @GetMapping("/workouts/{id}/sets/add")
    public String showAddSetForm(Set set) {
        return "add-set";
    }

//    @PostMapping("/workouts/{id}/sets/add")
//    public String addSet(Set set, BindingResult result, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "add-set";
//        }
//        setService.saveSet(set);
//        redirectAttributes.addFlashAttribute("sets", setService.getAllSets());
//        return "redirect:/workouts/sets";
//    }

    @PostMapping("/workouts/{id}/sets/add")
    public ModelAndView addSet(@PathVariable("id") Long id, @ModelAttribute("set") Set set,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("add-set");
        }
        set.setWorkout(workoutRepository.findById(id).orElse(null));
        set.setNumberOfReps(set.getNumberOfReps() == null ? 0 : set.getNumberOfReps());
        setRepository.save(set);
        model.addAttribute("workoutId", id);
        return new ModelAndView("sets", "sets", setService.getAllSets());
    }

//    @GetMapping("/workouts/sets/edit/{id}")
//    public String showUpdateSetForm(@PathVariable("id") Long id, Model model) {
//        Set set = setService.getSetsById(id);
//        model.addAttribute("set", new Set());
//        return "update-set";
//    }
//
//    @PostMapping("/workouts/sets/update/{id}")
//    public String updateSet(@PathVariable("id") Long id, Set set, BindingResult result,
//                                Model model) {
//        setService.updateSet(id, set);
//        model.addAttribute("sets", setService.getAllSets());
//        return "sets";
//    }
//
//    @GetMapping("/workouts/sets/delete/{id}")
//    public String deleteSet(@PathVariable("id") Long id, Model model) {
//        Set set = setService.getSetsById(id);
//        setService.deleteSet(id);
//        model.addAttribute("sets", setService.getAllSets());
//        return "sets";
//    }
}