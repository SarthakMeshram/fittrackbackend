package com.sarthak.fittrackbackend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.fittrackbackend.dto.request.CreateExerciseRequest;
import com.sarthak.fittrackbackend.dto.request.UpdateExerciseRequest;
import com.sarthak.fittrackbackend.entity.Exercise;
import com.sarthak.fittrackbackend.service.ExerciseService;

import jakarta.validation.Valid;

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
            @Valid @RequestBody CreateExerciseRequest request,
            Principal principal) {

        return exerciseService.createExercise(
                request,
                principal.getName());
    }

    @GetMapping("/{workoutId}/exercises")
    public List<Exercise> getExercises(
            @PathVariable Long workoutId,
            Principal principal) {

        return exerciseService.getExercises(
                workoutId,
                principal.getName());
    }

    @PatchMapping("/{id}/complete")
    public Exercise completeExercise(
            @PathVariable Long id,
            Principal principal) {

        return exerciseService.completeExercise(
                id,
                principal.getName());
    }

    @DeleteMapping("/{id}")
    public String deleteExercise(
            @PathVariable Long id,
            Principal principal) {

        exerciseService.deleteExercise(
                id,
                principal.getName());

        return "Exercise deleted successfully";
    }

    @PutMapping("/{id}")
    public Exercise updateExercise(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExerciseRequest request,
            Principal principal) {

        return exerciseService.updateExercise(
                id,
                request,
                principal.getName());
    }
}