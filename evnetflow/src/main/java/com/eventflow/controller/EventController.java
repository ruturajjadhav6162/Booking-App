package com.eventflow.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventflow.model.dto.event.CreateEventRequest;
import com.eventflow.model.dto.event.EventResponse;
import com.eventflow.model.entity.enums.EventStatus;
import com.eventflow.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public EventResponse create(
            @RequestBody @Valid CreateEventRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName(); // from JWT
        return eventService.create(request, email);
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Page<EventResponse> getAll(Pageable pageable) {
        return eventService.getAll(null, null, null, pageable);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public EventResponse getById(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EventResponse update(@PathVariable Long id,
                                @RequestBody CreateEventRequest req) {
        return eventService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancel(@PathVariable Long id) {
        eventService.cancel(id);
        return "Event cancelled";
    }

    @GetMapping("/filter/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Page<EventResponse> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String venue,
            @RequestParam(required = false) EventStatus status,
            Pageable pageable
    ) {
        return eventService.getAll(name, venue, status, pageable);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<EventResponse> search(@RequestParam String name) {
        return eventService.search(name);
    }
}