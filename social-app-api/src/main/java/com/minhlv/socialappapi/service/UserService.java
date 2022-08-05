package com.minhlv.socialappapi.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.minhlv.socialappapi.entity.SystemUser;

@Service
public interface UserService {

    SystemUser saveChangePass(SystemUser userEntity, String oldPassword, String newPassword, String repeatPassword);

    Map<String, Object> saveChangePassUser(String username, String newPassword);

    SystemUser findByUsername(String username);

    SystemUser saveUser(SystemUser userEntity);

}
