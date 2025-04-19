package com.example.mini_project.service;

import com.example.mini_project.model.Participant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IParticipantService {
    Participant save(Participant participant);
    Participant update(Participant participant);
    void delete(Integer id);
    Participant findById(Integer id);
    List<Participant> findAll();
    Page<Participant> findAll(Pageable pageable);
}
