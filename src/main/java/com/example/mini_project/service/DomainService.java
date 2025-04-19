package com.example.mini_project.service;

import com.example.mini_project.model.Domain;
import com.example.mini_project.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainService implements IDomainService {
    @Override
    public List<Domain> findAllDomains() {
        return domainRepository.findAll();
    }

    @Autowired
    private DomainRepository domainRepository;

    @Override
    public Domain save(Domain domain) {
        return domainRepository.save(domain);
    }

    @Override
    public Domain update(Domain domain) {
        return domainRepository.save(domain);
    }

    @Override
    public void delete(Integer id) {
        domainRepository.deleteById(id);
    }

    @Override
    public Domain findById(Integer id) {
        Optional<Domain> domain = domainRepository.findById(id);
        return domain.orElse(null);
    }

    @Override
    public Page<Domain> findAll(Pageable pageable) {
        return domainRepository.findAll(pageable);
    }


}
