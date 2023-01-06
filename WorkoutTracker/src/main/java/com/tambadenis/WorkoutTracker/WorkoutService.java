package com.tambadenis.WorkoutTracker;

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
        return workoutRepository.findById(id).orElse(null);
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
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

