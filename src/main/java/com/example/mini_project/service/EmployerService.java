package com.example.mini_project.service;

import com.example.mini_project.model.Employer;
import com.example.mini_project.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService implements IEmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public Employer save(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public Employer update(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public void delete(Integer id) {
        employerRepository.deleteById(id);
    }

    @Override
    public Employer findById(Integer id) {
        Optional<Employer> employer = employerRepository.findById(id);
        return employer.orElse(null);
    }

    @Override
    public Page<Employer> findAll(Pageable pageable) {
        return employerRepository.findAll(pageable);
    }

    @Override
    public List<Employer> findAll() {
        return employerRepository.findAll();
    }


}
