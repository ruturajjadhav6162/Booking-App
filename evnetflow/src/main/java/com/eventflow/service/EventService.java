package com.eventflow.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eventflow.model.dto.event.CreateEventRequest;
import com.eventflow.model.dto.event.EventResponse;
import com.eventflow.model.entity.Event;
import com.eventflow.model.entity.User;
import com.eventflow.model.entity.enums.EventStatus;
import com.eventflow.repository.EventRepository;
import com.eventflow.repository.UserRepository;
import com.eventflow.util.EventSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventResponse create(CreateEventRequest request, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = Event.builder()
                .name(request.name())
                .venue(request.venue())
                .eventDate(request.eventDate())
                .totalSeats(request.totalSeats())
                .availableSeats(request.totalSeats())
                .price(request.price())
                .status(EventStatus.UPCOMING)
                .build();
        event.setCreatedBy(user);

        return EventResponse.from(eventRepository.save(event));
    }

    // public EventResponse getById(Long id) {
    //     return eventRepository.findById(id)
    //             .map(EventResponse::from)
    //             .orElseThrow(() -> new RuntimeException("Event not found"));
    // }

    public EventResponse update(Long id, CreateEventRequest req) {

        Event event = eventRepository.findById(id)
            .orElseThrow();
        event.setName(req.name());
        event.setVenue(req.venue());
        event.setEventDate(req.eventDate());
        event.setTotalSeats(req.totalSeats());
        event.setPrice(req.price());
        eventRepository.save(event);
        return eventRepository.findEventResponseById(id);
    }

    public EventResponse getById(Long id) {
        return eventRepository.findEventResponseById(id);
    }
    public void cancel(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        event.setStatus(EventStatus.CANCELLED);
        eventRepository.save(event);
    }

    public Page<EventResponse> getAll(String name, String venue, EventStatus status, Pageable pageable) {
        return eventRepository.findAll(EventSpecification.filter(name, venue, status), pageable)
                .map(EventResponse::from);
    }

    public List<EventResponse> search(String name) {
        return eventRepository.findAll(EventSpecification.filter(name, null, null))
                .stream()
                .map(EventResponse::from)
                .toList();
    }

}