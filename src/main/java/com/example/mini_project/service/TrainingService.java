package com.example.mini_project.service;

import com.example.mini_project.model.Training;
import com.example.mini_project.repository.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
public class TrainingService implements ITrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Training saveTraining(Training training) {
        return trainingRepository.save(training);
    }


    @Override
    public Page<Training> getAllTrainings(Pageable pageable) {
        return trainingRepository.findAll(pageable);
    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id).orElse(null);
    }

    @Override
    public Training updateTraining(Training training) {
        return trainingRepository.save(training);
    }


    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }

    @Override
    public Page<Training> searchByTitle(String title, Pageable pageable) {
        return trainingRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public List<Training> getTrainingListByTrainer(int trainer_id) {
        return trainingRepository.findByTrainerId(trainer_id);
    }
}
