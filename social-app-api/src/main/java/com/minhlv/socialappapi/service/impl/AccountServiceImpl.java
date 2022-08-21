package com.minhlv.socialappapi.service.impl;

import com.minhlv.socialappapi.dto.requestDTO.AccountUpdateDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.repository.UserRepository;
import com.minhlv.socialappapi.service.AccountService;
import com.minhlv.socialappapi.utils.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	private final UserRepository userRepository;

	public AccountServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public APIResult updateAvatar(MultipartFile multipartFile) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SystemUserEntity user = userRepository.findByUsername(username);
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

		/*user.getAccountEntity().setUserAvatar(fileName);
		SystemUserEntity savedUser = userRepository.save(user);*/
		/*String uploadDir = "user-photos/" + user.getId();*/
		try {
			//FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			user.getAccountEntity().setUserAvatar(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
			userRepository.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new APIResult(fileName);
	}

	@Override
	public APIResult updateFullName(AccountUpdateDTO payload) {
		return null;
	}

	@Override
	public APIResult updateCoverImg() {
		return null;
	}


	public PostEntity changeAvatarPost(){

		return new PostEntity();
	}
}
