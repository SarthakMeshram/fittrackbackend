package com.sarthak.fittrackbackend.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateExerciseRequest {

    @NotNull(message = "Workout ID is required")
    private Long workoutId;

    @NotBlank(message = "Exercise name is required")
    private String exerciseName;

    @NotBlank(message = "Muscle group is required")
    private String muscleGroup;

    @NotNull(message = "Sets are required")
    @Min(value = 1, message = "Sets must be at least 1")
    private Integer sets;

    @NotNull(message = "Reps are required")
    @Min(value = 1, message = "Reps must be at least 1")
    private Integer reps;

    @NotNull(message = "Weight is required")
    @PositiveOrZero(message = "Weight cannot be negative")
    private BigDecimal weight;
}