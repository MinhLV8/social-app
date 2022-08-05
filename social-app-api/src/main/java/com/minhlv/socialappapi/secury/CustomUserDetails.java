package com.minhlv.socialappapi.secury;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.minhlv.socialappapi.entity.SystemUser;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = -3595426590795716449L;

    private SystemUser user;

    HashMap<String, Long> permission = new HashMap<>();

    public CustomUserDetails(SystemUser user) {
        this.user = user;
    }

    public boolean hasPermission(String key) {
        boolean canReload = false;
        // check key in permission
        if (permission.containsKey(key)) {
            long currentTime = System.currentTimeMillis();
            if (Math.abs(permission.get(key)) < currentTime - 1 * 60 * 1000) {
                // reload quyen
                canReload = true;
            }
        } else {
            canReload = true;
        }

        if (canReload) {
            permission.put(key, System.currentTimeMillis());
        }

        return permission.get(key) > 0;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    @Override
    public boolean isEnabled() {
        return user.getAccount().getIsEnable() == 1;
        // return true;
    }
}
