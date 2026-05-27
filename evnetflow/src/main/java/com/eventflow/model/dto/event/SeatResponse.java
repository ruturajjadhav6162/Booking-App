package com.eventflow.model.dto.event;

import com.eventflow.model.entity.Seat;

public record SeatResponse(Long id, String seatNumber, String status) {

	public static SeatResponse from(Seat seat) {
		return new SeatResponse(seat.getId(), seat.getSeatNumber(), seat.getStatus().name());
	}
}
