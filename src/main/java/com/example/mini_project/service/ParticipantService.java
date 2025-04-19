package com.example.mini_project.service;

import com.example.mini_project.model.Participant;
import com.example.mini_project.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService implements IParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant update(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public void delete(Integer id) {
        participantRepository.deleteById(id);
    }

    @Override
    public Participant findById(Integer id) {
        Optional<Participant> participant = participantRepository.findById(id);
        return participant.orElse(null);
    }

    @Override
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    @Override
    public Page<Participant> findAll(Pageable pageable) {
        return participantRepository.findAll(pageable);
    }
}
