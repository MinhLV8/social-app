package com.minhlv.socialappapi.secury;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionsEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        // log.info("User {} trying to access with permission {}",
        // customUserDetails.getUsername(),
        // permission.toString());
        return customUserDetails.hasPermission(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        // log.info("User {} trying to access {}-{} with permission {}",
        // customUserDetails.getUsername(),
        // targetType,
        // targetId,
        // permission.toString());
        return customUserDetails.hasPermission(permission.toString());
    }
}
