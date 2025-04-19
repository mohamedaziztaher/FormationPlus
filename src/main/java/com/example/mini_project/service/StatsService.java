package com.example.mini_project.service;


import com.example.mini_project.model.Training;
import com.example.mini_project.repository.ParticipantRepository;
import com.example.mini_project.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatsService implements IStatsService {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Map<String, Long> getTrainingsPerYear(int year) {
        Map<String, Long> result = new HashMap<>();
        result.put(String.valueOf(year), trainingRepository.countByYear(year));
        return result;
    }

    @Override
    public Map<String, Long> getTrainingsPerDomain() {
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : trainingRepository.countByDomain()) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    @Override
    public Map<String, Long> getTrainingsPerStructure() {
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : trainingRepository.countByStructure()) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    @Override
    public Map<String, Long> getParticipantsPerTraining() {
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : participantRepository.countByTraining()) {
            result.put(String.valueOf(row[0]), (Long) row[1]);
        }
        return result;
    }

    @Override
    public Map<String, Long> getParticipantsPerDomain() {
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : participantRepository.countByDomain()) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    @Override
    public List<Training> getTrainingsByTrainer(int trainerId) {
        return trainingRepository.findByTrainerId(trainerId);
    }


    @Override
    public List<Training> getTrainingsByDomain(Long domainId) {
        return trainingRepository.findByDomainId(domainId);
    }

    @Override
    public Map<String, Object> getAnnualReport(int year) {
        Map<String, Object> report = new HashMap<>();
        report.put("trainingsPerYear", getTrainingsPerYear(year));
        report.put("trainingsPerDomain", getTrainingsPerDomain());
        report.put("trainingsPerStructure", getTrainingsPerStructure());
        report.put("participantsPerTraining", getParticipantsPerTraining());
        report.put("participantsPerDomain", getParticipantsPerDomain());
        return report;
    }
}
