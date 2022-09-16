package com.minhlv.socialappapi.service.impl;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.dto.UserDataDTO;
import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.entity.SystemRoleEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.exception.CustomException;
import com.minhlv.socialappapi.repository.AccountRepository;
import com.minhlv.socialappapi.repository.ImageRepository;
import com.minhlv.socialappapi.repository.PostRepository;
import com.minhlv.socialappapi.repository.RoleRepository;
import com.minhlv.socialappapi.repository.UserRepository;
import com.minhlv.socialappapi.security.JwtTokenProvider;
import com.minhlv.socialappapi.service.UserService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.APIResult.MSG;
import com.minhlv.socialappapi.utils.AuthContext;
import com.minhlv.socialappapi.utils.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SystemUserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private final AuthContext authContext = new AuthContext();

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    public SystemUserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager,
            RoleRepository roleRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public APIResult signin(String username, String password) {
        APIResult result = new APIResult();

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateJwtToken(authentication);
            CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            Map<String, Object> re = new HashMap<>();
            result.setMessage(200, MSG.SUCCESS);
            re.put("token", jwt);
            re.put("info", ((CustomUserDetailsImpl) authentication.getPrincipal()).getAccountEntity());
            re.put("roles", roles);
            result.setData(re);
            return result;
        } catch (DisabledException ex) {
            result.setMessage(403, MSG.ACCESS_DENIED);
            return result;
        } catch (AuthenticationException e) {
            result.setMessage(403, "Invalid username/password supplied");
            return result;
        }
    }

    @Override
    @Transactional
    public APIResult signup(UserDataDTO appUser) {
        APIResult result = new APIResult();
        try {
            if (!userRepository.existsByUsername(appUser.getUsername())) {
                SystemUserEntity user = modelMapper.map(appUser, SystemUserEntity.class);
                AccountEntity account = modelMapper.map(appUser, AccountEntity.class);
                account.setFullName(appUser.getFirstName() + " " + appUser.getSurName());

                Set<SystemRoleEntity> roles = new HashSet<>(
                        Collections.singletonList(roleRepository.findByRole("ROLE_USER")));
                user.setPassword(passwordEncoder.encode(appUser.getPassword()));
                user.setRoles(roles);

                account.setUser(user);
                user.setAccountEntity(account);
                userRepository.save(user);

                InputStream avtDf = new ClassPathResource("./avatar-default.png").getInputStream();

                String avtPath = FileUploadUtil.saveFile("uploads/photos/" + user.getUsername(), "avatar-default.png",
                        (MultipartFile) avtDf);

                SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword())));
                Map<String, Object> re = new HashMap<>();
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
                String jwt = jwtTokenProvider.generateJwtToken(authentication);
                CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
                List<String> rolesStr = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());
                result.setMessage(200, MSG.SUCCESS);
                re.put("token", jwt);
                re.put("info", ((CustomUserDetailsImpl) authentication.getPrincipal()).getAccountEntity());
                re.put("roles", rolesStr);
                result.setData(re);
            } else {
                result.setMessage(500, "Username is already in use");
            }
            return result;
        } catch (Exception e) {
            result.setMessage(500, MSG.UNEXPECTED_ERROR_OCCURRED);
            return result;
        }
    }

    @Override
    public APIResult delete(String username) {
        APIResult result = new APIResult();
        try {
            SystemUserEntity user = userRepository.findByUsername(username);
            if (user == null) {
                result.setMessage(404, MSG.UNEXPECTED_ERROR_OCCURRED);
                return result;
            }
            if (!Objects.equals(user.getId(), authContext.getCurrentUser().getId())) {
                result.setMessage(403, MSG.ACTION_FORBIDDEN);
                return result;
            }
            SystemUserEntity userDel = userRepository.deleteByUsername(username);
            result.setData(userDel, MSG.SUCCESS);
            return result;
        } catch (Exception e) {
            result.setMessage(99, MSG.UNEXPECTED_ERROR_OCCURRED);
            return result;
        }

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
