package com.minhlv.socialappapi.service.impl;

import com.minhlv.socialappapi.dto.requestDTO.AccountUpdateDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.repository.ImageRepository;
import com.minhlv.socialappapi.repository.UserRepository;
import com.minhlv.socialappapi.service.AccountService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	private final UserRepository userRepository;

	private final ImageRepository imageRepository;

	public AccountServiceImpl(UserRepository userRepository, ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
	}

	@Override
	public APIResult updateAvatar(MultipartFile multipartFile) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SystemUserEntity user = userRepository.findByUsername(username);
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		try {
			user.getAccountEntity().setUserAvatar(FileUploadUtil.compressImage(multipartFile.getBytes()
			));
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
	public APIResult updateCoverImg(MultipartFile multipartFile) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SystemUserEntity user = userRepository.findByUsername(username);
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		try {
			user.getAccountEntity().setUserCover(FileUploadUtil.compressImage(multipartFile.getBytes()));
			userRepository.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new APIResult(fileName);
	}


	public PostEntity changeAvatarPost() {

		return new PostEntity();
	}

	public PostEntity changeCoverPost() {

		return new PostEntity();
	}
}
