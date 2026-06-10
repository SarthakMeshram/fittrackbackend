package com.sarthak.fittrackbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.fittrackbackend.dto.CreateWorkoutRequest;
import com.sarthak.fittrackbackend.dto.UpdateWorkoutRequest;
import com.sarthak.fittrackbackend.entity.Workout;
import com.sarthak.fittrackbackend.service.ExerciseService;
import com.sarthak.fittrackbackend.service.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    public WorkoutController(
            WorkoutService workoutService,
            ExerciseService exerciseService) {

        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public Workout createWorkout(
            @RequestBody CreateWorkoutRequest request) {

        return workoutService.createWorkout(request);
    }

    @GetMapping("/user/{userId}")
    public List<Workout> getWorkouts(
            @PathVariable Long userId) {

        return workoutService.getUserWorkouts(userId);
    }

    @PutMapping("/{workoutId}")
    public Workout updateWorkout(
            @PathVariable Long workoutId,
            @RequestBody UpdateWorkoutRequest request) {

        return workoutService.updateWorkout(
                workoutId,
                request);
    }
    @DeleteMapping("/{workoutId}")
public String deleteWorkout(
        @PathVariable Long workoutId) {

    workoutService.deleteWorkout(workoutId);

    return "Workout deleted successfully";
}
}