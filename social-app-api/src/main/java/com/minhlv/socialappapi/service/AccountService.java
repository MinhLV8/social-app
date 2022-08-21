package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.dto.requestDTO.AccountUpdateDTO;
import com.minhlv.socialappapi.utils.APIResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AccountService {

	APIResult updateAvatar(MultipartFile multipartFile);

	APIResult updateFullName(AccountUpdateDTO payload);

	APIResult updateCoverImg();
}
