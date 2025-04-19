package com.example.mini_project.service;

import com.example.mini_project.model.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IStructureService {
    Structure save(Structure structure);
    Structure update(Structure structure);
    void delete(Integer id);
    Structure findById(Integer id);
    Page<Structure> findAll(Pageable pageable);

    List<Structure> findAllStructures();
}
