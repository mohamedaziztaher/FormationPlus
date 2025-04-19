package com.example.mini_project.service;

import com.example.mini_project.model.Profil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IProfilService {
    Profil save(Profil profil);
    Profil update(Profil profil);
    void delete(Integer id);
    Profil findById(Integer id);
    Page<Profil> findAll(Pageable pageable);
}
