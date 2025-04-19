package com.example.mini_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenericErrorController {
    @GetMapping("/generic-error")
    public String showGenericErrorPage() {
        return "generic";
    }
}
