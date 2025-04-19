package com.example.mini_project.repository;

import com.example.mini_project.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
   
}
