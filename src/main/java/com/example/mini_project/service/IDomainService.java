package com.example.mini_project.service;

import com.example.mini_project.model.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDomainService {
    Domain save(Domain domain);
    Domain update(Domain domain);
    void delete(Integer id);
    Domain findById(Integer id);
    Page<Domain> findAll(Pageable pageable);
    public List<Domain> findAllDomains();
}
