package com.sarthak.fittrackbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarthak.fittrackbackend.entity.Exercise;

public interface ExerciseRepository
        extends JpaRepository<Exercise, Long> {

    List<Exercise> findByWorkoutId(Long workoutId);
}