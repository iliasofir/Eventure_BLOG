package com.fst.info.Eventure_App.services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.info.Eventure_App.models.Categorie;
import com.fst.info.Eventure_App.repositories.CategorieRepository;

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

   
    public void update(Categorie category) {
        Optional<Categorie> existingCategory = repo.findById(category.getId());
        if (existingCategory.isPresent()) {
            Categorie updatedCategory = existingCategory.get();
            updatedCategory.setTitle(category.getTitle()); // Mettez à jour les champs nécessaires
            repo.save(updatedCategory);
        } else {
            throw new RuntimeException("Categorie avec ID " + category.getId() + " n'existe pas.");
        }
    }

   
}