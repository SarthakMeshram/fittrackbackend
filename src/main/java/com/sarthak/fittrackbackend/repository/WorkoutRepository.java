package com.sarthak.fittrackbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarthak.fittrackbackend.entity.Workout;

public interface WorkoutRepository
        extends JpaRepository<Workout, Long> {

    List<Workout> findByUserId(Long userId);
}