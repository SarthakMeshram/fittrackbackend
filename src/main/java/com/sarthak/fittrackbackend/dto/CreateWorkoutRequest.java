package com.sarthak.fittrackbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWorkoutRequest {

    private Long userId;

    private String workoutName;
}