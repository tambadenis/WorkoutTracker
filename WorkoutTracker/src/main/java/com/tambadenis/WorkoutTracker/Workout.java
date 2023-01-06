package com.tambadenis.WorkoutTracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_id_seq")
    @SequenceGenerator(name = "workout_id_seq", sequenceName = "workout_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "date")
    @NotNull
    private Date date;
    @Column(name = "type")
    private String type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workout", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public Workout(String dateString, String type) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
        exercise.setWorkout(this);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}