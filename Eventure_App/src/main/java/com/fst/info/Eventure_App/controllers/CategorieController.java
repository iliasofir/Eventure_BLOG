package com.fst.info.Eventure_App.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fst.info.Eventure_App.models.Categorie;
import com.fst.info.Eventure_App.services.CategorieService;

@Controller
public class CategorieController {

    @Autowired
    private CategorieService service;

    @GetMapping("/categories")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", service.findAll());
        model.addAttribute("category", new Categorie()); // Add this line to bind the form
        return "categories";
    }

    // Add a new category
    @PostMapping("/categories")
    public String addCategorie(@ModelAttribute Categorie category) {
        service.addCategorie(category);
        return "redirect:/categories";
    }

    // Handle delete via POST (for HTML forms)
    @PostMapping("/categories/delete/{id}")
    public String deleteCategorieViaPost(@RequestParam("id") Long id) {
        service.deleteCategorieById(id);
        return "redirect:/categories";
    }

    // Handle update via POST (for HTML forms)
    @PostMapping("/categories/update/{id}")
    public String updateCategorieViaPost(@RequestParam("id") Long id, @RequestParam("title") String title) {
        Categorie category = service.findCategorieById(id);
        category.setTitle(title);
        service.addCategorie(category);
        return "redirect:/categories";
    }

}