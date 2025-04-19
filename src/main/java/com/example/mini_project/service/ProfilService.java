package com.example.mini_project.service;

import com.example.mini_project.model.Profil;
import com.example.mini_project.repository.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfilService implements IProfilService {
    @Autowired
    private ProfilRepository profilRepository;

    @Override
    public Profil save(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public Profil update(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public void delete(Integer id) {
        profilRepository.deleteById(id);
    }

    @Override
    public Profil findById(Integer id) {
        Optional<Profil> profil = profilRepository.findById(id);
        return profil.orElse(null);
    }

    @Override
    public Page<Profil> findAll(Pageable pageable) {
        return profilRepository.findAll(pageable);
    }


}
