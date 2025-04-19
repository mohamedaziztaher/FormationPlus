package com.example.mini_project.controller;

import com.example.mini_project.service.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.time.Year;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatsController {
    @Autowired
    private IStatsService statsService;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping({ "/trainings-per-year", "/trainings-per-year/{year}" })
    public Map<String, Long> getTrainingsPerYear(
            @PathVariable(required = false) Integer year) {
        if (year == null)
            year = Year.now().getValue();
        return statsService.getTrainingsPerYear(year);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping("/trainings-per-domain")
    public Map<String, Long> getTrainingsPerDomain() {
        return statsService.getTrainingsPerDomain();
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping("/trainings-per-structure")
    public Map<String, Long> getTrainingsPerStructure() {
        return statsService.getTrainingsPerStructure();
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping("/participants-per-training")
    public Map<String, Long> getParticipantsPerTraining() {
        return statsService.getParticipantsPerTraining();
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping("/participants-per-domain")
    public Map<String, Long> getParticipantsPerDomain() {
        return statsService.getParticipantsPerDomain();
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping("/trainings-by-trainer/{trainerId}")
    public List<?> getTrainingsByTrainer(@PathVariable int trainerId) {
        return statsService.getTrainingsByTrainer(trainerId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping("/trainings-by-domain/{domainId}")
    public List<?> getTrainingsByDomain(@PathVariable Long domainId) {
        return statsService.getTrainingsByDomain(domainId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @ResponseBody
    @GetMapping("/annual-report/{year}")
    public Map<String, Object> getAnnualReport(@PathVariable int year) {
        return statsService.getAnnualReport(year);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/view")
    public String showStatsPage(Model model) {
        model.addAttribute("templateName", "statistics/affichStatistics");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "statistics");
        model.addAttribute("breadcrumb", "Dashboard");
        return "dashboard";
    }
}
