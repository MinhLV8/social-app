package com.minhlv.socialappapi.utils;

import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.service.impl.CustomUserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthContext {

	private String username;
	private SystemUserEntity currentUser;
	private AccountEntity currentAccount;

	public AuthContext() {
        // TODO document why this constructor is empty
    }

	private SystemUserEntity fetchUserInfo() {
		SystemUserEntity systemUser = new SystemUserEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			systemUser.setUsername(auth.getName());
		}
		return systemUser;
	}

	public SystemUserEntity getCurrentUser() {
		return ((CustomUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser();
	}

	public AccountEntity getCurrentAccount() {
		return ((CustomUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getAccountEntity();
	}

	public String getUsername() {
		return fetchUserInfo().getUsername();
	}

	@Override
	public String toString() {
		return "AuthContext [username=" + username + ", currentUser=" + currentUser + ", currentAccount="
				+ currentAccount + "]";
	}
}
