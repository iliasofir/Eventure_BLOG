package com.example.demov2.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demov2.models.Categorie;
import com.example.demov2.repositories.CategorieRepository;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository repo;

    // Find all categories
    public List<Categorie> findAll() {
        return repo.findAll();
    }

    // Add a new category
    public Categorie addCategorie(Categorie categorie) {
        return repo.save(categorie);
    }

    // Delete a category by ID
    public void deleteCategorieById(Long id) {
        repo.deleteById(id);
    }

    // Find a category by ID
    public Categorie findCategorieById(Long id) {
        return repo.findById(id).orElse(null);
    }

   
}