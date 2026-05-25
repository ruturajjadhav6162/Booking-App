package com.eventflow.repository;

import com.eventflow.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

	List<Seat> findByEventIdOrderBySeatNumberAsc(Long eventId);

	List<Seat> findByIdInAndEventId(List<Long> ids, Long eventId);
}
