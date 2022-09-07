package com.minhlv.socialappapi.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.minhlv.socialappapi.dto.UserDataDTO;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.utils.APIResult;

@Service
public interface UserService {

    APIResult signin(String username, String password);

    String signup(UserDataDTO appUser);

    void delete(String username);

    SystemUserEntity whoami(HttpServletRequest req);

    SystemUserEntity search(String username);

    String refresh(String username);

    SystemUserEntity saveChangePass(SystemUserEntity userEntity, String oldPassword, String newPassword,
            String repeatPassword);

    Map<String, Object> saveChangePassUser(String username, String newPassword);

    SystemUserEntity findByUsername(String username);

    SystemUserEntity saveUser(SystemUserEntity userEntity);

}
