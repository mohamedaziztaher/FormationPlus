package com.example.mini_project.controller;

import com.example.mini_project.model.Structure;
import com.example.mini_project.model.Trainer;
import com.example.mini_project.service.IStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/structures")
public class StructureController {
    @Autowired
    private IStructureService structureService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Structure create(@RequestBody Structure structure) {
        return structureService.save(structure);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Structure update(@RequestBody Structure structure) {
        return structureService.update(structure);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        structureService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public Structure getById(@PathVariable Integer id) {
        return structureService.findById(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/view")
    public String showStructuresPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Structure> structurePage = structureService.findAll(pageable);

        model.addAttribute("structures", structurePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", structurePage.getTotalPages());

        model.addAttribute("templateName", "structures/affichStructures");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "structures");
        model.addAttribute("breadcrumb", "Dashboard");

        return "dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String showCreateStructureForm() {
        return "admin/structures"; // Or a separate template if you have one for creation
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditStructureForm(@PathVariable Integer id) {
        return "admin/structures"; // Or a separate template if you have one for editing
    }
}
