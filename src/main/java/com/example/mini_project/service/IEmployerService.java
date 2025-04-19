package com.example.mini_project.service;

import com.example.mini_project.model.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployerService {
    Employer save(Employer employer);
    Employer update(Employer employer);
    void delete(Integer id);
    Employer findById(Integer id);
    Page<Employer> findAll(Pageable pageable);
    List<Employer> findAll();
}
