package com.eventflow.model.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessPaymentRequest(
		@NotNull Long bookingId,
		@NotBlank String idempotencyKey
) {
}
