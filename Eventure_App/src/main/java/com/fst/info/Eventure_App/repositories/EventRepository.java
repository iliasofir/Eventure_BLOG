package com.fst.info.Eventure_App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fst.info.Eventure_App.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    
} 
