package com.repill.was.global.config;

import lombok.Getter;

@Getter
public class ServiceAccountPreAuthenticationPrincipal {

	private String userId;

	private String role;

	private boolean isAdmin;

	public ServiceAccountPreAuthenticationPrincipal(String userId, String roles, boolean isAdmin) {
		this.userId = userId;
		this.role = roles;
		this.isAdmin = isAdmin;
	}

	public ServiceAccountPreAuthenticationPrincipal(String userId, String role) {
		this(userId, role, false);
	}
}
