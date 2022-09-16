package com.minhlv.socialappapi.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final AuthContext authContext;

    private final PostService postService;

    @Autowired
    public PostController(PostService postService, AuthContext authContext) {
        this.postService = postService;
        this.authContext = authContext;
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

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${PostController.createPost}", response = APIResult.class, authorizations = {
            @Authorization(value = "jwt")})
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResult createPost(@RequestPart("post") String payload,
            @RequestPart("images") List<MultipartFile> images) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CreatePostDTO postRequest = objectMapper.readValue(payload, CreatePostDTO.class);
            return postService.save(modelMapper.map(postRequest, PostEntity.class), images, authContext);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

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

    @GetMapping(value = "/timeline")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${PostController.getListPost}", response = APIResult.class, authorizations = {
            @Authorization(value = "jwt")})
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResult getTimeLine(@RequestParam(name = "id", required = true) long id) {
        return postService.list(authContext);
    }

    @GetMapping(value = "/newfeel")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${PostController.getListPost}", response = APIResult.class, authorizations = {
            @Authorization(value = "jwt")})
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResult getNewFeel() {
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
        return postService.updatePrivacyPost(payload, authContext);
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
