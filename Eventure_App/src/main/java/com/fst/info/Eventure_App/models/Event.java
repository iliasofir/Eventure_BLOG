package com.fst.info.Eventure_App.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Identifiant unique
    private String title; // Titre de l'événement
    private String description; // Description de l'événement
    private Date dateEvent; // Date de l'événement
    private Date dateCreate; // Date de création
    private String image; // URL ou chemin de l'image
}