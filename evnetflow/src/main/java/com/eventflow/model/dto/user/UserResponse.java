package com.eventflow.model.dto.user;

import com.eventflow.model.entity.User;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
		Long id,
		String name,
		String email,
		String role,
		LocalDateTime createdAt
) {
	public static UserResponse from(User user) {
		return new UserResponse(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getRole().name(),
				user.getCreatedAt()
		);
	}
}
