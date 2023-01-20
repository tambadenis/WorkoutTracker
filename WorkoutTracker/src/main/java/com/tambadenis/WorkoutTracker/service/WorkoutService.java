package com.tambadenis.WorkoutTracker.service;

import com.tambadenis.WorkoutTracker.model.Set;
import com.tambadenis.WorkoutTracker.model.Workout;
import com.tambadenis.WorkoutTracker.repository.SetRepository;
import com.tambadenis.WorkoutTracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout getWorkoutById(Long id) {
        Workout workout = workoutRepository.findById(id).orElse(null);
        if(workout != null) {
            workout.getSets().size();
        }
        return workout;
    }

    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    public Workout updateWorkout(Long id, Workout workout) {
        Workout existingWorkout = workoutRepository.findById(id).orElse(null);
        if (existingWorkout != null) {
            existingWorkout.setDate(workout.getDate());
            existingWorkout.setType(workout.getType());
            return workoutRepository.save(existingWorkout);
        } else {
            return null;
        }
    }
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}

