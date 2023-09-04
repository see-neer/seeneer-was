package com.repill.was.global.config;

import com.repill.was.member.entity.account.AccountId;
import lombok.Getter;

@Getter
public class ServiceAccountPreAuthenticationPrincipal {

	private AccountId userId;

	private String role;

	private boolean isAdmin;

	public ServiceAccountPreAuthenticationPrincipal(AccountId userId, String roles, boolean isAdmin) {
		this.userId = userId;
		this.role = roles;
		this.isAdmin = isAdmin;
	}

	public ServiceAccountPreAuthenticationPrincipal(AccountId userId, String role) {
		this(userId, role, false);
	}
}
