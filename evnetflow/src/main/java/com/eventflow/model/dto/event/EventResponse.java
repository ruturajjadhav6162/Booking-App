package com.eventflow.model.dto.event;

import com.eventflow.model.entity.Event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventResponse(
		Long id,
		String name,
		String venue,
		LocalDateTime eventDate,
		int totalSeats,
		int availableSeats,
		BigDecimal price,
		String status,
		LocalDateTime createdAt
) {
	public static EventResponse from(Event event) {
		return new EventResponse(
				event.getId(),
				event.getName(),
				event.getVenue(),
				event.getEventDate(),
				event.getTotalSeats(),
				event.getAvailableSeats(),
				event.getPrice(),
				event.getStatus().name(),
				event.getCreatedAt()
		);
	}
}
