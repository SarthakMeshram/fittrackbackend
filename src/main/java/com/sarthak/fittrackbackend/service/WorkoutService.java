package com.sarthak.fittrackbackend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            CreateWorkoutRequest request,
            String email) {

        User user = getUserByEmail(email);

        Workout workout = new Workout();

        workout.setWorkoutName(request.getWorkoutName());
        workout.setWorkoutDate(LocalDate.now());
        workout.setCreatedAt(LocalDateTime.now());
        workout.setUser(user);

        return workoutRepository.save(workout);
    }

//     public List<Workout> getUserWorkouts(Long userId) {
//         return workoutRepository.findByUserId(userId);
//     }

    public List<Workout> getMyWorkouts(String email) {

        User user = getUserByEmail(email);

        return workoutRepository.findByUserId(user.getId());
    }

    public Workout updateWorkout(
            Long workoutId,
            UpdateWorkoutRequest request,
            String email) {

        Workout workout = workoutRepository
                .findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Workout not found"));

        User user = getUserByEmail(email);

        validateOwnership(workout, user);

        workout.setWorkoutName(request.getWorkoutName());

        return workoutRepository.save(workout);
    }

    public void deleteWorkout(
            Long workoutId,
            String email) {

        Workout workout = workoutRepository
                .findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Workout not found"));

        User user = getUserByEmail(email);

        validateOwnership(workout, user);

        workoutRepository.delete(workout);
    }

    private User getUserByEmail(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"));
    }

    private void validateOwnership(
            Workout workout,
            User user) {

        if (!workout.getUser()
                .getId()
                .equals(user.getId())) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Access denied");
        }
    }
}