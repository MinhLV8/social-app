package com.minhlv.socialappapi.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.exception.CustomException;
import com.minhlv.socialappapi.repository.UserRepository;
import com.minhlv.socialappapi.security.JwtTokenProvider;
import com.minhlv.socialappapi.service.UserService;
import com.minhlv.socialappapi.utils.APIResult;

@Service
public class SystemUserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    public SystemUserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public APIResult signin(String username, String password) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateJwtToken(authentication);
            CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            APIResult result = new APIResult();
            result.setMessage("Thành công.");
            Map<String, Object> re = new HashMap<>();
            re.put("jwt", "Bearer " + jwt);
            re.put("roles", roles);
            result.setData(re);
            return result;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String signup(SystemUserEntity appUser) {
        if (!userRepository.existsByUsername(appUser.getUsername())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            return jwtTokenProvider.generateToken(
                    (CustomUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public SystemUserEntity search(String username) {
        SystemUserEntity appUser = userRepository.findByUsernameOrSdt(username);
        if (appUser == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return appUser;
    }

    @Override
    public SystemUserEntity whoami(HttpServletRequest req) {
        return userRepository.findByUsernameOrSdt(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    @Override
    public String refresh(String username) {
        return jwtTokenProvider.generateToken(
                (CustomUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails());
    }

    @Override
    public SystemUserEntity saveChangePass(SystemUserEntity userEntity, String oldPassword, String newPassword,
            String repeatPassword) {
        return null;
    }

    @Override
    public Map<String, Object> saveChangePassUser(String username, String newPassword) {
        return Collections.emptyMap();
    }

    @Override
    public SystemUserEntity findByUsername(String username) {
        return null;
    }

    @Override
    public SystemUserEntity saveUser(SystemUserEntity userEntity) {
        return null;
    }
}
