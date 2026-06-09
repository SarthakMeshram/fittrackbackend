package com.sarthak.fittrackbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.fittrackbackend.dto.CreateExerciseRequest;
import com.sarthak.fittrackbackend.entity.Exercise;
import com.sarthak.fittrackbackend.service.ExerciseService;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(
            ExerciseService exerciseService) {

        this.exerciseService = exerciseService;
    }

    @PostMapping
    public Exercise createExercise(
            @RequestBody
            CreateExerciseRequest request) {

        return exerciseService
                .createExercise(request);
    }

    @GetMapping("/{workoutId}")
    public List<Exercise> getExercises(
            @PathVariable Long workoutId) {

        return exerciseService
                .getExercises(workoutId);
    }
    @PatchMapping("/{id}/complete")
    public Exercise completeExercise(@PathVariable Long id) {

        return exerciseService.completeExercise(id);
    }
    @DeleteMapping("/{id}")
    public String deleteExercise(@PathVariable Long id) {

        exerciseService.deleteExercise(id);

        return "Exercise deleted successfully"; 
    }
}