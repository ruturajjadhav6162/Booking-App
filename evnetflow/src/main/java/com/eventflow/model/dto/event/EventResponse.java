package com.eventflow.model.dto.event;

import com.eventflow.model.entity.Event;
import com.eventflow.model.entity.enums.EventStatus;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record EventResponse(
		Long id,
        String name,
        String venue,
        LocalDateTime eventDate,
        Integer totalSeats,
        Integer availableSeats,
        BigDecimal price,
        EventStatus status,
        String createdBy,
        LocalDateTime createdAt
) {
	public static EventResponse from(Event event) {
		String email = event.getCreatedBy() != null
                ? event.getCreatedBy().getEmail()
                : null;
		return new EventResponse(
				event.getId(),
				event.getName(),
				event.getVenue(),
				event.getEventDate(),
				event.getTotalSeats(),
				event.getAvailableSeats(),
				event.getPrice(),
				event.getStatus(),
				email,
				event.getCreatedAt()
		);
	}
}
