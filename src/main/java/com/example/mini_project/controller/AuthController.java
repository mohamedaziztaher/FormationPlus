package com.example.mini_project.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mini_project.model.Training;
import com.example.mini_project.service.ITrainingService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AuthController {
    private final ITrainingService trainingService;

    public AuthController(ITrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("")
    public String root() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
    @GetMapping("/home")
    public String home(Authentication authentication, Model model, HttpSession session,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        String role = null;

        if (authentication != null) {
            String username = authentication.getName();
            role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(Object::toString)
                    .orElse(null);

            model.addAttribute("login", username);
            model.addAttribute("role", role);
            session.setAttribute("username", username);
            session.setAttribute("role", role);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logged in user roles: " + auth.getAuthorities());
        if (role != null) {
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_USER")) {
                return getString(model, page, size, trainingService);
            } else if (role.equals("ROLE_MANAGER")) {
                model.addAttribute("templateName", "statistics/affichStatistics");
                model.addAttribute("fragmentName", "content");
                model.addAttribute("activePage", "statistics"); 
                model.addAttribute("breadcrumb", "Dashboard");
                return "dashboard";
            }
        }

        return "redirect:/login";
    }

    static String getString(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, ITrainingService trainingService) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Training> trainingPage = trainingService.getAllTrainings(pageable);

        model.addAttribute("trainings", trainingPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", trainingPage.getTotalPages());
        model.addAttribute("templateName", "trainings/affichTrainings");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "trainings");
        model.addAttribute("breadcrumb", "Dashboard");
        return "dashboard";
    }
}