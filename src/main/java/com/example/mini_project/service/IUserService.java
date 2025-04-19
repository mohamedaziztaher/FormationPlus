package com.example.mini_project.service;

import com.example.mini_project.model.Training;
import com.example.mini_project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Page<User> getAllUsers(Pageable pageable);
    Optional<User> getUserById(int id);
    User updateUser(int id, User user);
    void deleteUser(int id);
}
