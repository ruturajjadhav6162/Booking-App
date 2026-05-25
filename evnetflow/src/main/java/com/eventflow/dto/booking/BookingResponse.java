package com.eventflow.dto.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record BookingResponse(
		Long id,
		Long eventId,
		String eventName,
		String venue,
		LocalDateTime eventDate,
		List<BookingSeatDto> seats,
		BigDecimal totalPrice,
		String status,
		LocalDateTime holdExpiresAt,
		LocalDateTime createdAt
) {
}
