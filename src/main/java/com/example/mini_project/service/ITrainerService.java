package com.example.mini_project.service;

import com.example.mini_project.model.Trainer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.List;

public interface ITrainerService {
    Page<Trainer> getAll(Pageable pageable);
    Trainer getById(Integer id);
    Trainer update(Integer id, Trainer trainer);
    void delete(Integer id);

    Trainer create(@Valid Trainer trainer, MultipartFile imageFile, Integer employerId) throws IOException;

    ResponseEntity<?> uploadImage(Integer id, MultipartFile file) throws IOException;

    List<Trainer> findAllTrainers();
}
