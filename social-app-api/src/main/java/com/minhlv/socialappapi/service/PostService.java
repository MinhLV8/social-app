package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.utils.APIResult;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
	APIResult createPost(PostEntity payload);

	APIResult updatePrivacyPost(short privacy);

	APIResult updatePost(PostEntity payload);

	APIResult deletePost(long[] ids);
}
