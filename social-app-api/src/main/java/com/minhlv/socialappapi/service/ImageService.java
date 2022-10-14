package com.minhlv.socialappapi.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.entity.ImageEntity;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

@Service
public interface ImageService extends BaseService<APIResult, ImageEntity> {
    APIResult save(List<MultipartFile> multipartFiles, AuthContext authContext);

    APIResult save(List<MultipartFile> multipartFiles, PostEntity post, AuthContext authContext);

    Resource loadFileAsResource(String filename);

}
