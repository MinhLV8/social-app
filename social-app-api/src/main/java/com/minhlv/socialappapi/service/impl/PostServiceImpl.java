package com.minhlv.socialappapi.service.impl;

import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.service.PostService;
import com.minhlv.socialappapi.utils.APIResult;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	@Override
	public APIResult createPost(PostEntity payload) {
		APIResult re = new APIResult();
		//payload.setAccounts();
		return null;
	}

	@Override
	public APIResult updatePrivacyPost(short privacy) {
		return null;
	}

	@Override
	public APIResult updatePost(PostEntity payload) {
		return null;
	}

	@Override
	public APIResult deletePost(long[] ids) {
		return null;
	}
}
