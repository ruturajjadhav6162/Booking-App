package com.eventflow.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.eventflow.model.entity.Event;
import com.eventflow.model.entity.enums.EventStatus;

import jakarta.persistence.criteria.Predicate;

public class EventSpecification {

    public static Specification<Event> filter(String name, String venue, EventStatus status) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(cb.like(cb.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"));
            }

            if (venue != null) {
                predicates.add(cb.like(cb.lower(root.get("venue")),
                        "%" + venue.toLowerCase() + "%"));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}