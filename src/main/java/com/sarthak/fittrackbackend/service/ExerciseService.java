package com.sarthak.fittrackbackend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sarthak.fittrackbackend.dto.CreateExerciseRequest;
import com.sarthak.fittrackbackend.dto.UpdateExerciseRequest;
import com.sarthak.fittrackbackend.entity.Exercise;
import com.sarthak.fittrackbackend.entity.User;
import com.sarthak.fittrackbackend.entity.Workout;
import com.sarthak.fittrackbackend.repository.ExerciseRepository;
import com.sarthak.fittrackbackend.repository.UserRepository;
import com.sarthak.fittrackbackend.repository.WorkoutRepository;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public ExerciseService(
            ExerciseRepository exerciseRepository,
            WorkoutRepository workoutRepository,
            UserRepository userRepository) {

        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public Exercise createExercise(
            CreateExerciseRequest request,
            String email) {

        Workout workout = workoutRepository
                .findById(request.getWorkoutId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Workout not found"));

        User user = getUserByEmail(email);

        validateWorkoutOwnership(workout, user);

        Exercise exercise = new Exercise();

        exercise.setExerciseName(request.getExerciseName());
        exercise.setMuscleGroup(request.getMuscleGroup());
        exercise.setSets(request.getSets());
        exercise.setReps(request.getReps());
        exercise.setWeight(request.getWeight());
        exercise.setCompleted(false);
        exercise.setWorkout(workout);

        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getExercises(
            Long workoutId,
            String email) {

        Workout workout = workoutRepository
                .findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Workout not found"));

        User user = getUserByEmail(email);

        validateWorkoutOwnership(workout, user);

        return exerciseRepository.findByWorkoutId(workoutId);
    }

    public Exercise completeExercise(
            Long id,
            String email) {

        Exercise exercise = exerciseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise not found"));

        User user = getUserByEmail(email);

        validateOwnership(exercise, user);

        exercise.setCompleted(true);

        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(
            Long id,
            String email) {

        Exercise exercise = exerciseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise not found"));

        User user = getUserByEmail(email);

        validateOwnership(exercise, user);

        exerciseRepository.delete(exercise);
    }

    public Exercise updateExercise(
            Long id,
            UpdateExerciseRequest request,
            String email) {

        Exercise exercise = exerciseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise not found"));

        User user = getUserByEmail(email);

        validateOwnership(exercise, user);

        exercise.setExerciseName(request.getExerciseName());
        exercise.setMuscleGroup(request.getMuscleGroup());
        exercise.setSets(request.getSets());
        exercise.setReps(request.getReps());
        exercise.setWeight(request.getWeight());

        return exerciseRepository.save(exercise);
    }

    private User getUserByEmail(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"));
    }

    private void validateOwnership(
            Exercise exercise,
            User user) {

        if (!exercise.getWorkout()
                .getUser()
                .getId()
                .equals(user.getId())) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Access denied");
        }
    }

    private void validateWorkoutOwnership(
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