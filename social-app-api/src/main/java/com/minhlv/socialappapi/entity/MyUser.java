package com.minhlv.socialappapi.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MyUser extends User {

	private static final long serialVersionUID = 8688523693773849202L;

	private Long id;
	private String fullname;

	public MyUser(String username, String password, boolean isEnabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, isEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
