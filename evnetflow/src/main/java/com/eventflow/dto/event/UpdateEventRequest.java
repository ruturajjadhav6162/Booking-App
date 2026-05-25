package com.eventflow.dto.event;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateEventRequest(
		@NotBlank String name,
		@NotBlank String venue,
		@NotNull LocalDateTime eventDate,
		@Min(1) int totalSeats,
		@NotNull @DecimalMin("0.0") BigDecimal price
) {
}
