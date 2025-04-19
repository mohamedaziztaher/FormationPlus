package com.example.mini_project.controller;

import com.example.mini_project.model.Employer;
import com.example.mini_project.model.Structure;
import com.example.mini_project.service.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/employers")
public class EmployerController {
    @Autowired
    private IEmployerService employerService;

    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    public Employer create(@RequestBody Employer employer) {
        return employerService.save(employer);
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping
    public Employer update(@RequestBody Employer employer) {
        return employerService.update(employer);
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        employerService.delete(id);
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public Employer getById(@PathVariable Integer id) {
        return employerService.findById(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/view")
    public String showEmployersPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employer> employerPage = employerService.findAll(pageable);

        model.addAttribute("employers", employerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employerPage.getTotalPages());

        model.addAttribute("templateName", "employers/affichEmployers");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "employers");
        model.addAttribute("breadcrumb", "Dashboard");

        return "dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String showCreateEmployerForm() {
        return "admin/users"; // Or a separate template if you have one for creation
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditEmployerForm(@PathVariable Integer id) {
        return "admin/users"; // Or a separate template if you have one for editing
    }
}
