package com.minhlv.socialappapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.dto.requestdto.AccountUpdateDTO;
import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.utils.APIResult;

@Service
public interface AccountService {

    APIResult getAccount();

    APIResult updateAvatar(MultipartFile multipartFile);

    APIResult updateFullName(AccountUpdateDTO payload);

    APIResult updateCoverImg(MultipartFile multipartFile);

    APIResult updateAccount(AccountEntity payload);
}
