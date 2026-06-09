package com.sarthak.fittrackbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.fittrackbackend.dto.CreateWorkoutRequest;
import com.sarthak.fittrackbackend.entity.Workout;
import com.sarthak.fittrackbackend.service.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(
            WorkoutService workoutService) {

        this.workoutService = workoutService;
    }

    @PostMapping
    public Workout createWorkout(
            @RequestBody
            CreateWorkoutRequest request) {

        return workoutService.createWorkout(
                request);
    }

    @GetMapping("/{userId}")
    public List<Workout> getWorkouts(
            @PathVariable Long userId) {

        return workoutService
                .getUserWorkouts(userId);
    }
}