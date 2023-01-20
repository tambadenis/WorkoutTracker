package com.tambadenis.WorkoutTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Set")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set_id_seq")
    @SequenceGenerator(name = "set_id_seq", sequenceName = "set_id_seq", allocationSize = 1)
    private Long id;
    @NotNull
    private String exerciseName;
    private Integer setNumber;
    @NotNull
    private Integer numberOfReps;
    @NotNull
    private double weightUsed;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Set() {
    }

    public Set(Workout workout, String exerciseName, int setNumber, int numberOfReps, double weightUsed) {
        this.exerciseName = exerciseName;
        this.setNumber = setNumber;
        this.numberOfReps = numberOfReps;
        this.weightUsed = weightUsed;
        this.workout = workout;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public double getWeightUsed() {
        return weightUsed;
    }

    public void setWeightUsed(double weightUsed) {
        this.weightUsed = weightUsed;
    }

    public Long getId() {
        return id;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    public Integer getNumberOfReps() {
        return numberOfReps;
    }

    public void setNumberOfReps(Integer numberOfReps) {
        this.numberOfReps = numberOfReps;
    }
}
