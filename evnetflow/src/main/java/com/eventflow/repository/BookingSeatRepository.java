package com.eventflow.repository;

import com.eventflow.model.entity.BookingSeat;
import com.eventflow.model.entity.BookingSeatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, BookingSeatId> {

	List<BookingSeat> findByBookingId(Long bookingId);
}
