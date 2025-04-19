package com.example.mini_project.service;

import com.example.mini_project.config.FileUploadConfig;
import com.example.mini_project.model.Employer;
import com.example.mini_project.model.Trainer;
import com.example.mini_project.repository.EmployerRepository;
import com.example.mini_project.repository.TrainerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainerService implements ITrainerService {
    private final TrainerRepository trainerRepository;
    private final EmployerRepository employerRepository;
    private final FileUploadConfig fileUploadConfig;

    public TrainerService(TrainerRepository trainerRepository, EmployerRepository employerRepository, FileUploadConfig fileUploadConfig) {
        this.trainerRepository = trainerRepository;
        this.employerRepository = employerRepository;
        this.fileUploadConfig = fileUploadConfig;
    }

    @Override
    public Page<Trainer> getAll(Pageable pageable) {
        return trainerRepository.findAll(pageable);
    }

    @Override
    public Trainer getById(Integer id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        return trainer.orElse(null);
    }
    @Override
    public Trainer update(Integer id, @Valid Trainer trainer) {
        if (!trainerRepository.existsById(id)) return null;
        trainer.setId(id);
        return trainerRepository.save(trainer);
    }

    @Override
    public void delete(Integer id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
        }
    }


    @Override
    public Trainer create(@Valid Trainer trainer, MultipartFile imageFile, Integer employerId) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Use absolute path for upload directory
            String uploadDir = Paths.get(fileUploadConfig.getUploadDir()).toAbsolutePath().toString();

            // Create directory structure if needed
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate safe filename
            String fileName = StringUtils.cleanPath(
                    UUID.randomUUID() + "_" + imageFile.getOriginalFilename()
            );

            // Save file using absolute path
            Path filePath = uploadPath.resolve(fileName);
            try (InputStream inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("Uploading to: " + uploadPath.toString());
            // Store only filename in database
            trainer.setImagePath(fileName);
        }

        if (employerId != null) {
            Employer employer = employerRepository.findById(employerId)
                    .orElseThrow(() -> new RuntimeException("Employer not found with ID: " + employerId));
            trainer.setEmployer(employer);
        }

        return trainerRepository.save(trainer);
    }
    @Override
    public ResponseEntity<?> uploadImage(Integer id, MultipartFile file) throws IOException {
        Optional<Trainer> trainerOpt = trainerRepository.findById(id);
        if (trainerOpt.isEmpty()) return ResponseEntity.notFound().build();

        Trainer trainer = trainerOpt.get();

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Image file is missing or empty.");
        }

        String uploadDir = "uploads/trainers/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + fileName;
        file.transferTo(new File(filePath));

        trainer.setImagePath(filePath);
        trainerRepository.save(trainer);

        return ResponseEntity.ok("Image uploaded successfully.");
    }


    @Override
    public List<Trainer> findAllTrainers() {
        return trainerRepository.findAll();
    }
}
