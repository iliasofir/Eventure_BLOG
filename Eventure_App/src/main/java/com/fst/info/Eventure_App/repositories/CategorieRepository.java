package com.fst.info.Eventure_App.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.info.Eventure_App.models.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    
} 