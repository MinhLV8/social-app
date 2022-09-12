package com.minhlv.socialappapi.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CustomUserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = -3595426590795716449L;
    private List<GrantedAuthority> authorities;
    private Long id;
    private String username;
    private String email;
    // private boolean isEnabled;
    private boolean isDelete;
    private SystemUserEntity user;
    private AccountEntity account;

    public CustomUserDetailsImpl(Long id, String username, String email, boolean isEnabled, boolean isDelete,
            SystemUserEntity user, List<GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        // this.isEnabled = isEnabled;
        this.isDelete = isDelete;
        this.user = user;
        this.account = user.getAccountEntity();
        this.authorities = authorities;
    }

    public static CustomUserDetailsImpl build(SystemUserEntity systemUserEntity) {
        List<GrantedAuthority> authorities = systemUserEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
        return new CustomUserDetailsImpl(systemUserEntity.getId(), systemUserEntity.getUsername(),
                systemUserEntity.getEmail(), systemUserEntity.getAccountEntity().getIsEnable() == 1,
                systemUserEntity.getAccountEntity().getIsDelete() == 0, systemUserEntity, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isEnabled() {
        return user.getAccountEntity().getIsEnable() == 1;
    }

    /*
     * public void setEnabled(short isEnabled) { this.isEnabled = isEnabled ==
     * 0; }
     */

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public SystemUserEntity getUser() {
        return user;
    }

    public void setUser(SystemUserEntity user) {
        this.user = user;
    }

    public AccountEntity getAccountEntity() {
        return this.account;
    }

    public void setAccountEntity() {
        this.account = this.user.getAccountEntity();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

}
