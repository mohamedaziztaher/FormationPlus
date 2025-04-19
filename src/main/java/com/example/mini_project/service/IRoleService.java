package com.example.mini_project.service;

import com.example.mini_project.model.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAllRoles();
    public Role findRoleById(long id);
}
