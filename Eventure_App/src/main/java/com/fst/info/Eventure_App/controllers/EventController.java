package com.fst.info.Eventure_App.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fst.info.Eventure_App.models.Event;
import com.fst.info.Eventure_App.services.EventService;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/{id}")
    public String getEventDetails(@PathVariable Long id, Model model) {
        Optional<Event> eventOptional = eventService.getEventById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            model.addAttribute("event", event);
            model.addAttribute("comments", event.getComments());
            model.addAttribute("interactions", event.getInteractions());
            model.addAttribute("participants", event.getParticipants());
            return "EventDetails";
        }
        return "redirect:/events";
    }
}
