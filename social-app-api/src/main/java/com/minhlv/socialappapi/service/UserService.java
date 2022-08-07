package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.entity.SystemUserEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public interface UserService {

    String signin(String username, String password);

    String signup(SystemUserEntity appUser);

    void delete(String username);

    SystemUserEntity whoami(HttpServletRequest req);

    SystemUserEntity search(String username);

    String refresh(String username);

    SystemUserEntity saveChangePass(SystemUserEntity userEntity, String oldPassword, String newPassword, String repeatPassword);

    Map<String, Object> saveChangePassUser(String username, String newPassword);

    SystemUserEntity findByUsername(String username);

    SystemUserEntity saveUser(SystemUserEntity userEntity);

}
