package com.fst.info.Eventure_App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.info.Eventure_App.models.Event;
import com.fst.info.Eventure_App.repositories.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository repo;

    public Optional<Event> getEventById(Long id) {
        return repo.findById(id);
    }
}
