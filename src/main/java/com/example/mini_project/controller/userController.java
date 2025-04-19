package com.example.mini_project.controller;



import com.example.mini_project.model.Role;
import com.example.mini_project.model.User;
import com.example.mini_project.service.IRoleService;
import com.example.mini_project.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/users")
public class userController {


    private final IUserService userService;
    private final IRoleService iRoleService;
    public userController(IUserService userService, IRoleService iRoleService) {
        this.userService = userService;
        this.iRoleService = iRoleService;
    }
    @ResponseBody

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            User updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view")
    public String showUser(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.getAllUsers(pageable);
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());

        model.addAttribute("templateName", "users/affichUsers");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "users");
        model.addAttribute("breadcrumb", "Dashboard");

        return "dashboard";
    }

}
