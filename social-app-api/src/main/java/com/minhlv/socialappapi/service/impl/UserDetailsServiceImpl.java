package com.minhlv.socialappapi.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.repository.RoleRepository;
import com.minhlv.socialappapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUserEntity systemUserEntity = userRepository.findByUsernameOrSdt(username);
        if (ObjectUtils.isEmpty(systemUserEntity)) {
            log.error("User not found! {}" + username);
            throw new UsernameNotFoundException(username);
        }
        return CustomUserDetailsImpl.build(systemUserEntity);
    }

}
