package com.eventflow.repository;

import com.eventflow.model.dto.event.EventResponse;
import com.eventflow.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    @Query("""
            SELECT new com.eventflow.model.dto.event.EventResponse(
                e.id,
                e.name,
                e.venue,
                e.eventDate,
                e.totalSeats,
                e.availableSeats,
                e.price,
                e.status,
                u.email,
                e.createdAt
            )
            FROM Event e
            JOIN e.createdBy u
            WHERE e.id = :id
            """)
    EventResponse findEventResponseById(@Param("id") Long id);
}
