package com.minhlv.socialappapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minhlv.socialappapi.dto.requestdto.CreatePostDTO;
import com.minhlv.socialappapi.dto.requestdto.LikePostDTO;
import com.minhlv.socialappapi.dto.requestdto.UpdatePrivacyPostDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.service.PostService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/post")
@Api(tags = "posts")
public class PostController {
	private final ModelMapper modelMapper = new ModelMapper();

	private final AuthContext authContext = new AuthContext();

	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = "/get")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.getPost}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult getPost(@RequestParam(required = false) long id) {
		return postService.find(id, authContext);
	}

	@PostMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.createPost}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult createPost(@RequestBody CreatePostDTO payload) {
		return postService.save(modelMapper.map(payload, PostEntity.class), authContext);
	}

	@GetMapping(value = "/list")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.getListPost}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult listPost() {
		return postService.list(authContext);
	}

	@PutMapping(value = "/privacy")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.updatePrivacyPost}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult updatePrivacyPost(@RequestBody UpdatePrivacyPostDTO payload) {
		return postService.updatePrivacyPost(payload);
	}

	@PutMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.updatePost}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult updatePost(@RequestBody CreatePostDTO payload) {
		return postService.update(modelMapper.map(payload, PostEntity.class), authContext);
	}

	@DeleteMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.deletePost}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult deletePost(@RequestBody long[] ids) {
		return postService.delete(ids, authContext);
	}

	@PutMapping(value = "/like")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${PostController.likePost}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult likePost(@RequestBody LikePostDTO payload) {
		return postService.likePost(payload);
	}

}
