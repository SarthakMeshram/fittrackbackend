package com.sarthak.fittrackbackend.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseRequest {

    private Long workoutId;

    private String exerciseName;

    private String muscleGroup;

    private Integer sets;

    private Integer reps;

    private BigDecimal weight;
}