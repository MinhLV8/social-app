package com.minhlv.socialappapi.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.service.impl.CustomUserDetailsImpl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class AuthContext {

    private String username;
    private SystemUserEntity currentUser;
    private AccountEntity currentAccount;

    public AuthContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Current Authentication: {}", auth);
        if (auth != null) {
            SystemUserEntity user = (SystemUserEntity) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            this.username = auth.getName();
            this.currentUser = user;
            this.currentAccount = this.currentUser.getAccountEntity();
            log.info("Current User: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            log.info("Current User: {}", this.currentUser.toString());
            log.info("Current Account: {}", this.currentAccount.toString());
        }

    }

    public SystemUserEntity getCurrentUser() {
        return ((CustomUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser();
    }

    public AccountEntity getCurrentAccount() {
        return ((CustomUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getAccountEntity();
    }
}
