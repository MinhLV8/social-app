package com.minhlv.socialappapi.controller;

import com.minhlv.socialappapi.dto.requestDTO.CreatePostDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.service.PostService;
import com.minhlv.socialappapi.utils.APIResult;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@Api(tags = "posts")
public class PostController {
	private final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private PostService postService;

	@PostMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.createPost}", response = CreatePostDTO.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult createPost(@RequestBody CreatePostDTO payload) {
		return postService.createPost(modelMapper.map(payload, PostEntity.class));
	}

	@PostMapping(value = "/privacy")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.updatePrivacyPost}", response = CreatePostDTO.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult updatePrivacyPost(@RequestBody short privacy) {
		return postService.updatePrivacyPost(privacy);
	}

	@PutMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.updatePost}", response = CreatePostDTO.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult updatePost(@RequestBody CreatePostDTO payload) {
		return postService.updatePost(modelMapper.map(payload, PostEntity.class));
	}

	@DeleteMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.updatePost}", response = CreatePostDTO.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult deletePost(@RequestBody long[] ids) {
		return postService.deletePost(ids);
	}
}
