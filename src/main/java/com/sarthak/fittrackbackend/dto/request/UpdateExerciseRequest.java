package com.sarthak.fittrackbackend.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateExerciseRequest {

    @NotBlank(message = "Exercise name is required")
    private String exerciseName;

    @NotBlank(message = "Muscle group is required")
    private String muscleGroup;

    @NotNull
    @Min(1)
    private Integer sets;

    @NotNull
    @Min(1)
    private Integer reps;

    @NotNull
    @PositiveOrZero
    private BigDecimal weight;
}