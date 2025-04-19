package com.example.mini_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.mini_project.service.UserService;
@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
  private UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String login, @RequestParam String password, @RequestParam String role, Model model) {
        try {
            userService.createUser(login, password, role);
            model.addAttribute("success", "User created successfully");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "register";
    }

}
