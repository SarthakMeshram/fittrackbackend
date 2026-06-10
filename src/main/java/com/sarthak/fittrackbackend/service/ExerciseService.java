package com.sarthak.fittrackbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarthak.fittrackbackend.dto.CreateExerciseRequest;
import com.sarthak.fittrackbackend.dto.UpdateExerciseRequest;
import com.sarthak.fittrackbackend.entity.Exercise;
import com.sarthak.fittrackbackend.entity.Workout;
import com.sarthak.fittrackbackend.repository.ExerciseRepository;
import com.sarthak.fittrackbackend.repository.WorkoutRepository;

@Service
public class ExerciseService {

        private final ExerciseRepository exerciseRepository;
        private final WorkoutRepository workoutRepository;

        public ExerciseService(
                        ExerciseRepository exerciseRepository,
                        WorkoutRepository workoutRepository) {

                this.exerciseRepository = exerciseRepository;
                this.workoutRepository = workoutRepository;
        }

        public Exercise createExercise(
                        CreateExerciseRequest request) {

                Workout workout = workoutRepository.findById(
                                request.getWorkoutId())
                                .orElseThrow();

                Exercise exercise = new Exercise();

                exercise.setExerciseName(
                                request.getExerciseName());

                exercise.setMuscleGroup(
                                request.getMuscleGroup());

                exercise.setSets(
                                request.getSets());

                exercise.setReps(
                                request.getReps());

                exercise.setWeight(
                                request.getWeight());

                exercise.setCompleted(false);

                exercise.setWorkout(workout);

                return exerciseRepository.save(exercise);
        }

        public List<Exercise> getExercises(
                        Long workoutId) {

                return exerciseRepository
                                .findByWorkoutId(workoutId);
        }

        public Exercise completeExercise(Long id) {

                Exercise exercise = exerciseRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Exercise not found"));

                exercise.setCompleted(true);

                return exerciseRepository.save(exercise);
        }

        public void deleteExercise(Long id) {

                Exercise exercise = exerciseRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Exercise not found"));

                exerciseRepository.delete(exercise);
        }

        public Exercise updateExercise(
                        Long id,
                        UpdateExerciseRequest request) {

                Exercise exercise = exerciseRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Exercise not found"));

                exercise.setExerciseName(request.getExerciseName());
                exercise.setMuscleGroup(request.getMuscleGroup());
                exercise.setSets(request.getSets());
                exercise.setReps(request.getReps());
                exercise.setWeight(request.getWeight());

                return exerciseRepository.save(exercise);
        }
}
