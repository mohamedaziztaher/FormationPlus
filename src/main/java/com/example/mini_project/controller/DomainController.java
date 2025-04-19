package com.example.mini_project.controller;

import com.example.mini_project.model.*;
import com.example.mini_project.service.*;
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
@RequestMapping("/domains")
public class DomainController {
    @Autowired
    private IDomainService domainService;

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    @PostMapping
    public Domain create(@RequestBody Domain domain) {
        return domainService.save(domain);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    @PutMapping
    public Domain update(@RequestBody Domain domain) {
        return domainService.update(domain);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        domainService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseBody
    @GetMapping("/{id}")
    public Domain getById(@PathVariable Integer id) {
        return domainService.findById(id);
    }



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/view")
    public String showDomainsPage(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Domain> domainPage = domainService.findAll(pageable);
        model.addAttribute("domains", domainPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", domainPage.getTotalPages());

        model.addAttribute("templateName", "domains/affichDomains");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "domains");
        model.addAttribute("breadcrumb", "Dashboard");

        return "dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String showCreateDomainForm() {
        return "admin/domains"; // Or a separate template if you have one for creation
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditDomainForm(@PathVariable Integer id) {
        return "admin/domains"; // Or a separate template if you have one for editing
    }
}
