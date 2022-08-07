package com.minhlv.socialappapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class CustomUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -3595426590795716449L;
	List<GrantedAuthority> authorities;
	private Long id;
	private String username;
	private String email;
	private boolean isEnabled;
	private boolean isDelete;
	@JsonIgnore
	private String password;

	public CustomUserDetailsImpl(Long id, String username, String email, boolean isEnabled, boolean isDelete, String password,
								 List<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.isEnabled = isEnabled;
		this.isDelete = isDelete;
		this.password = password;
		this.authorities = authorities;
	}

	public static CustomUserDetailsImpl build(SystemUserEntity systemUserEntity) {
		List<GrantedAuthority> authorities = systemUserEntity.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		return new CustomUserDetailsImpl(systemUserEntity.getId(),
				systemUserEntity.getUsername(), systemUserEntity.getEmail(),
				systemUserEntity.getAccountEntity().getIsEnable() == 1,
				systemUserEntity.getAccountEntity().getIsDelete() == 0,
				systemUserEntity.getPassword(),
				authorities);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
