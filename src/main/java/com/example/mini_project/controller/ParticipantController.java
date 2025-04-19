package com.example.mini_project.controller;

import com.example.mini_project.model.Participant;
import com.example.mini_project.model.Training;
import com.example.mini_project.service.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/participants")
public class ParticipantController {
    @Autowired
    private IParticipantService participantService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseBody
    @PostMapping
    public Participant create(@RequestBody Participant participant) {
        return participantService.save(participant);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseBody
    @PutMapping
    public Participant update(@RequestBody Participant participant) {
        return participantService.update(participant);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseBody
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        participantService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseBody
    @GetMapping("/{id}")
    public Participant getById(@PathVariable Integer id) {
        return participantService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseBody
    @GetMapping
    public Page<Participant> getAll(Pageable pageable) {
        return participantService.findAll(pageable);
    }
    @GetMapping("/view")
    public String showTrainingsPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Participant> participantPage = participantService.findAll(pageable);

        model.addAttribute("participants", participantPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", participantPage.getTotalPages());
        model.addAttribute("templateName", "participants/affichParticipants");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "participants");
        model.addAttribute("breadcrumb", "Dashboard");
        return "dashboard";
    }
}
