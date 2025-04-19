package com.example.mini_project.controller;

import com.example.mini_project.model.Profil;
import com.example.mini_project.model.Trainer;
import com.example.mini_project.service.IProfilService;
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
@RequestMapping("/profiles")
public class ProfilController {
    @Autowired
    private IProfilService profilService;

    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Profil create(@RequestBody Profil profil) {
        return profilService.save(profil);
    }

    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Profil update(@RequestBody Profil profil) {
        return profilService.update(profil);
    }

    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        profilService.delete(id);
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public Profil getById(@PathVariable Integer id) {
        return profilService.findById(id);
    }



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/view")
    public String showProfilsPage(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Profil> profilPage = profilService.findAll(pageable);

        model.addAttribute("profiles", profilPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", profilPage.getTotalPages());

        model.addAttribute("templateName", "profiles/affichProfiles");
        model.addAttribute("fragmentName", "content");
        model.addAttribute("activePage", "profiles");
        model.addAttribute("breadcrumb", "Dashboard");

        return "dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String showCreateProfilForm() {
        return "user/profil"; // Or a separate template if you have one for creation
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditProfilForm(@PathVariable Integer id) {
        return "user/profil"; // Or a separate template if you have one for editing
    }
}
