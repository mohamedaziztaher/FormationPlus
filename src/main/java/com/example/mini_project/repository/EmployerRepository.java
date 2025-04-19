package com.example.mini_project.repository;

import com.example.mini_project.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    // ...
}
