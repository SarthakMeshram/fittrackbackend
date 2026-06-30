package com.sarthak.fittrackbackend.controller;

import java.security.Principal;
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
            @RequestBody CreateWorkoutRequest request,
            Principal principal) {

        return workoutService.createWorkout(
                request,
                principal.getName());
    }

    // @GetMapping("/user/{userId}")
    // public List<Workout> getWorkouts(
    //         @PathVariable Long userId) {

    //     return workoutService.getUserWorkouts(userId);
    // }

    @PutMapping("/{workoutId}")
    public Workout updateWorkout(
            @PathVariable Long workoutId,
            @RequestBody UpdateWorkoutRequest request,
            Principal principal) {

        return workoutService.updateWorkout(
                workoutId,
                request,
                principal.getName());
    }

    @DeleteMapping("/{workoutId}")
    public String deleteWorkout(
            @PathVariable Long workoutId,
            Principal principal) {

        workoutService.deleteWorkout(
                workoutId,
                principal.getName());

        return "Workout deleted successfully";
    }

    @GetMapping("/my-workouts")
    public List<Workout> getMyWorkouts(
            Principal principal) {

        return workoutService.getMyWorkouts(
                principal.getName());
    }
}
