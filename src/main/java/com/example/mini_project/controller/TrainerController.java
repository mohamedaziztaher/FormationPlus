package com.example.mini_project.controller;

import com.example.mini_project.model.Trainer;
import com.example.mini_project.model.Training;
import com.example.mini_project.service.EmployerService;
import com.example.mini_project.service.ITrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/trainers")
public class TrainerController {
    private final ITrainerService trainerService;
    private final EmployerService employerService;

    public TrainerController(ITrainerService trainerService, EmployerService employerService) {
        this.trainerService = trainerService;
        this.employerService = employerService;
    }


    @GetMapping("/view")
    public String getAll(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Trainer> trainerPage = trainerService.getAll(pageable);

        model.addAttribute("trainers", trainerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", trainerPage.getTotalPages());

        model.addAttribute("templateName", "trainers/affichTrainers");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "trainers"); 
        model.addAttribute("breadcrumb", "Dashboard");

        return "dashboard";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getById(@PathVariable Integer id) {
        Trainer trainer = trainerService.getById(id);
        if (trainer == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(trainer);
    }

    @GetMapping("/new")
    public String afficheNewForm(Model model) {
        model.addAttribute("employers", employerService.findAll());
        model.addAttribute("trainer", new Trainer());
        return "trainers/addTrainer";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String add(
            @Valid @ModelAttribute Trainer trainer,
            BindingResult result,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("employerId") Integer employerId,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("employers", employerService.findAll());
            return "trainers/addTrainer";
        }

        try {
            trainerService.create(trainer, imageFile, employerId);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("employers", employerService.findAll());
            model.addAttribute("uploadError", "An error occurred while uploading the image.");
            return "trainers/addTrainer";
        }

        return "redirect:/trainers/view";
    }


    @ResponseBody
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Trainer> showUpdateForm(@PathVariable Integer id, @Valid @RequestBody Trainer trainer) {
        Trainer updated = trainerService.update(id, trainer);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
        //return "trainers/updateTrainer";
    }
 @PostMapping("/update/{id}")
    public String updtae(@PathVariable("id") int id, @ModelAttribute Trainer trainer) {
        trainerService.update(id, trainer);
        return "redirect:/view";
    }
    @ResponseBody
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Integer id) {
        trainerService.delete(id);
        return "redirect:/view";
    }

    @ResponseBody
    @PostMapping("/{id}/upload-image")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> uploadImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        return trainerService.uploadImage(id, file);
    }


}
