package com.example.mini_project.service;

import com.example.mini_project.model.Training;

import java.util.List;
import java.util.Map;

public interface IStatsService {
    Map<String, Long> getTrainingsPerYear(int year);
    Map<String, Long> getTrainingsPerDomain();
    Map<String, Long> getTrainingsPerStructure();
    Map<String, Long> getParticipantsPerTraining();
    Map<String, Long> getParticipantsPerDomain();
    List<Training> getTrainingsByTrainer(int trainerId);
    List<Training> getTrainingsByDomain(Long domainId);
    Map<String, Object> getAnnualReport(int year);
}
