package com.sarthak.fittrackbackend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sarthak.fittrackbackend.dto.CreateWorkoutRequest;
import com.sarthak.fittrackbackend.dto.UpdateWorkoutRequest;
import com.sarthak.fittrackbackend.entity.User;
import com.sarthak.fittrackbackend.entity.Workout;
import com.sarthak.fittrackbackend.repository.UserRepository;
import com.sarthak.fittrackbackend.repository.WorkoutRepository;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(
            WorkoutRepository workoutRepository,
            UserRepository userRepository) {

        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public Workout createWorkout(
            CreateWorkoutRequest request) {

        User user =
                userRepository.findById(
                        request.getUserId())
                        .orElseThrow();

        Workout workout = new Workout();

        workout.setWorkoutName(
                request.getWorkoutName());

        workout.setWorkoutDate(
                LocalDate.now());

        workout.setCreatedAt(
                LocalDateTime.now());

        workout.setUser(user);

        return workoutRepository.save(workout);
    }

    public List<Workout> getUserWorkouts(
            Long userId) {

        return workoutRepository.findByUserId(
                userId);
    }

    public Workout updateWorkout(
        Long workoutId,
        UpdateWorkoutRequest request) {

    Workout workout = workoutRepository
            .findById(workoutId)
            .orElseThrow(() ->
                    new RuntimeException("Workout not found"));

    workout.setWorkoutName(
            request.getWorkoutName());

    return workoutRepository.save(workout);
}

public void deleteWorkout(Long workoutId) {

    Workout workout = workoutRepository
            .findById(workoutId)
            .orElseThrow(() ->
                    new RuntimeException("Workout not found"));

    workoutRepository.delete(workout);
}
}
