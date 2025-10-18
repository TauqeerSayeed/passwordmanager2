package com.example.passwordmanager.controller;

import com.example.passwordmanager.model.PasswordEntry;
import com.example.passwordmanager.service.PasswordEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final PasswordEntryService service;

    public MainController(PasswordEntryService service) {
        this.service = service;
    }

    // Login page: Spring Security will redirect here if unauthenticated
    // The manual @PostMapping("/login") must be DELETED or COMMENTED OUT.
    @GetMapping({"/", "/login"}) // Maps both / and /login to the login page
    public String loginPage() {
        return "login";
    }

    // The entire @PostMapping("/login") method MUST BE REMOVED!
    // Spring Security now handles /login submissions.

    // -------------------------------------------------------------------
    // Spring Security will ensure ONLY users with ROLE_ADMIN can access this.
    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("entries", service.getAll());
        return "admin";
    }

    // Spring Security will ensure ONLY authenticated users (ROLE_USER or ROLE_ADMIN) can access this.
    @GetMapping("/user")
    public String userPage(Model model) {
        model.addAttribute("entries", service.getAll());
        return "user";
    }

    // All CRUD operations are protected under the /admin/** rule
    @PostMapping("/admin/add")
    public String addEntry(@ModelAttribute PasswordEntry entry) {
        service.save(entry);
        return "redirect:/admin";
    }

    @PostMapping("/admin/update/{id}")
    public String updateEntry(@PathVariable Long id, @ModelAttribute PasswordEntry entry) {
        PasswordEntry existing = service.getById(id).orElse(null);
        if (existing != null) {
            existing.setSystem(entry.getSystem());
            existing.setEnvironment(entry.getEnvironment());
            existing.setUrl(entry.getUrl());
            existing.setUsername(entry.getUsername());
            existing.setPassword(entry.getPassword());
            service.save(existing);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteEntry(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin";
    }
}