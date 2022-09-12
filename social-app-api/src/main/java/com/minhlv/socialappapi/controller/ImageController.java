package com.minhlv.socialappapi.controller;

import com.minhlv.socialappapi.service.ImageService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@Api(tags = "images")
public class ImageController {

	private final ModelMapper modelMapper = new ModelMapper();

	private final AuthContext authContext = new AuthContext();

	private final ImageService imageService;

	@Autowired
	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@GetMapping(value = "/get")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${ImageController.getImage}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult getImage(@RequestParam(required = false) long id) {
		return imageService.find(id, authContext);
	}

	@PostMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${ImageController.saveImage}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult saveImage(@RequestBody MultipartFile[] multipartFiles) {
		return imageService.save(multipartFiles, authContext);
	}

	@GetMapping(value = "/list")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${ImageController.getListImage}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult listImage() {
		return imageService.list(authContext);
	}

	@DeleteMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${ImageController.deleteImages}", response = APIResult.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult deleteImages(@RequestBody long[] ids) {
		return imageService.delete(ids, authContext);
	}
}
