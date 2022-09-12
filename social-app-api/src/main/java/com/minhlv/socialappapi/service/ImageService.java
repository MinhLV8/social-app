package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.entity.ImageEntity;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService extends BaseService<APIResult, ImageEntity> {
	APIResult save(MultipartFile[] multipartFiles, AuthContext authContext);
}
