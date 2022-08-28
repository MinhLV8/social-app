package com.minhlv.socialappapi.service.impl;

import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.repository.PostRepository;
import com.minhlv.socialappapi.service.PostService;
import com.minhlv.socialappapi.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public APIResult createPost(PostEntity payload) {
		APIResult re = new APIResult();
		re.setData(postRepository.save(payload));
		re.setMessage("Thành công.");
		return re;
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
