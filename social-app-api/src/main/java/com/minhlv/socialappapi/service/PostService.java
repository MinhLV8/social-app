package com.minhlv.socialappapi.service;

import org.springframework.stereotype.Service;

import com.minhlv.socialappapi.dto.requestdto.LikePostDTO;
import com.minhlv.socialappapi.dto.requestdto.UpdatePrivacyPostDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.utils.APIResult;

@Service
public interface PostService extends BaseService<APIResult, PostEntity> {
    APIResult updatePrivacyPost(UpdatePrivacyPostDTO payload);
    APIResult likePost(LikePostDTO payload);
}
