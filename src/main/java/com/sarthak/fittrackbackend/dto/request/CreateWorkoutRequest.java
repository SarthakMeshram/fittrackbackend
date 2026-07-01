package com.sarthak.fittrackbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWorkoutRequest {

    @NotBlank(message = "Workout name is required")
    @Size(min = 3, max = 50,
          message = "Workout name must be between 3 and 50 characters")
    private String workoutName;
    private Long userId;
}

