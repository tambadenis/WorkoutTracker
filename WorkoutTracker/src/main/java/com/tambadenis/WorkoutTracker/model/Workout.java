package com.tambadenis.WorkoutTracker.model;

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
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<Set> sets;

    public Workout() {
    }

    public Workout(String dateString, String type) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.type = type;
        this.sets = new ArrayList<>();
    }

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
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

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public void addSet(Set set) {
        set.setWorkout(this);
        sets.add(set);
    }

    public void removeSet(Set set) {
        sets.remove(set);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", sets=" + sets +
                '}';
    }
}