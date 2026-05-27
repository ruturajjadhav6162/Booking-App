package com.eventflow.model.dto.payment;

import com.eventflow.model.entity.PaymentRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
		String idempotencyKey,
		Long bookingId,
		String status,
		BigDecimal amount,
		LocalDateTime processedAt
) {
	public static PaymentResponse from(PaymentRecord record) {
		return new PaymentResponse(
				record.getIdempotencyKey(),
				record.getBooking().getId(),
				record.getStatus().name(),
				record.getAmount(),
				record.getProcessedAt()
		);
	}
}
