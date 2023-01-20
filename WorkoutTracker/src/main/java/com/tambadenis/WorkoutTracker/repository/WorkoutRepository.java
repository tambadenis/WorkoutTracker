package com.tambadenis.WorkoutTracker.repository;

import com.tambadenis.WorkoutTracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
