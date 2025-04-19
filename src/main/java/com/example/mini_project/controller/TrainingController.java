package com.example.mini_project.controller;

import com.example.mini_project.model.Training;
import com.example.mini_project.service.IDomainService;
import com.example.mini_project.service.IStructureService;
import com.example.mini_project.service.ITrainerService;
import com.example.mini_project.service.ITrainingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;

import static com.example.mini_project.controller.AuthController.getString;

@Controller
@RequestMapping("/trainings")
public class TrainingController {

    private final ITrainingService trainingService;
    private final IDomainService domainService;
    private final IStructureService structureService;
    private final ITrainerService trainerService;

    public TrainingController(ITrainingService trainingService, IDomainService domainService, IStructureService structureService, ITrainerService trainerService) {
        this.trainingService = trainingService;
        this.domainService = domainService;
        this.structureService = structureService;
        this.trainerService = trainerService;
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Training training = trainingService.getTrainingById(id);
        model.addAttribute("domains", domainService.findAllDomains());
        model.addAttribute("structures", structureService.findAllStructures());
        model.addAttribute("trainers", trainerService.findAllTrainers());
        model.addAttribute("training", training);
        return "trainings/updateTraining";
    }

    @PostMapping("/update/{id}")
    public String updateTraining(@PathVariable("id") int id, @ModelAttribute Training training) {
        trainingService.updateTraining(training);
        return "redirect:/trainings/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        trainingService.deleteTraining(id);
        return "redirect:/trainings/view";
    }

    @GetMapping("/view")
    public String showTrainingsPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        return getString(model, page, size, trainingService);
    }

    @GetMapping("/new")
    public String afficheNewForm(Model model) {
        model.addAttribute("domains", domainService.findAllDomains());
        model.addAttribute("structures", structureService.findAllStructures());
        model.addAttribute("trainers", trainerService.findAllTrainers());
        model.addAttribute("training", new Training());
        return "trainings/addTraining";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String add(@Valid @ModelAttribute Training training, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("domains", domainService.findAllDomains());
            model.addAttribute("structures", structureService.findAllStructures());
            model.addAttribute("trainers", trainerService.findAllTrainers());
            return "trainings/addTraining";
        }
        trainingService.saveTraining(training);
        return "redirect:/trainings/view";
    }

    @GetMapping("/search")
    public String getTrainingsByTitle(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        int size = 5; // number of items per page
        Page<Training> trainingPage;

        if (title != null && !title.isEmpty()) {
            trainingPage = trainingService.searchByTitle(title, PageRequest.of(page, size));
            model.addAttribute("title", title); // to preserve the search term in the UI
        } else {
            trainingPage = trainingService.getAllTrainings(PageRequest.of(page, size));
        }

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
