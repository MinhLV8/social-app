package com.minhlv.socialappapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.dto.requestdto.LikePostDTO;
import com.minhlv.socialappapi.dto.requestdto.UpdatePrivacyPostDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

@Service
public interface PostService extends BaseService<APIResult, PostEntity> {
    APIResult updatePrivacyPost(UpdatePrivacyPostDTO payload, AuthContext authContext);
    APIResult likePost(LikePostDTO payload);
    APIResult save(PostEntity post, List<MultipartFile> images, AuthContext authContext);
    APIResult timeLine(PostEntity post, List<MultipartFile> images, AuthContext authContext);
    APIResult newFeel(PostEntity post, List<MultipartFile> images, AuthContext authContext);
}
