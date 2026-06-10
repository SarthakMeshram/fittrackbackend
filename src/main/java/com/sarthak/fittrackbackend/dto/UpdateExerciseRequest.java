package com.sarthak.fittrackbackend.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateExerciseRequest {

    private String exerciseName;
    private String muscleGroup;
    private Integer sets;
    private Integer reps;
    private BigDecimal weight;
}