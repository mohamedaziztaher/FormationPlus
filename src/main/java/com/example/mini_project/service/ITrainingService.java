package com.example.mini_project.service;

import com.example.mini_project.model.Training;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ITrainingService {
    Training saveTraining(Training training);
    Page<Training> getAllTrainings(Pageable pageable);
    Training getTrainingById(Long id);
    Training updateTraining(Training training);
    void deleteTraining(Long id);
    Page<Training>searchByTitle(String title,Pageable pageable);
    List<Training> getTrainingListByTrainer(int trainer_id);
}
