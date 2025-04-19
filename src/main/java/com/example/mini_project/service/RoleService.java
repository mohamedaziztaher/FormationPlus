package com.example.mini_project.service;

import com.example.mini_project.model.Role;
import com.example.mini_project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    final
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {

        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findById(id).get();
    }
}
