package com.example.mini_project.service;

import com.example.mini_project.model.Structure;
import com.example.mini_project.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StructureService implements IStructureService {
    @Autowired
    private StructureRepository structureRepository;

    @Override
    public Structure save(Structure structure) {
        return structureRepository.save(structure);
    }

    @Override
    public Structure update(Structure structure) {
        return structureRepository.save(structure);
    }

    @Override
    public void delete(Integer id) {
        structureRepository.deleteById(id);
    }

    @Override
    public Structure findById(Integer id) {
        Optional<Structure> structure = structureRepository.findById(id);
        return structure.orElse(null);
    }

    @Override
    public Page<Structure> findAll(Pageable pageable) {
        return structureRepository.findAll(pageable);
    }

    @Override
    public List<Structure> findAllStructures() {
        return structureRepository.findAll();
    }

}
