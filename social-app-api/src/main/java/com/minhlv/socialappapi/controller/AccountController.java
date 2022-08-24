package com.minhlv.socialappapi.controller;

import com.minhlv.socialappapi.dto.UserResponseDTO;
import com.minhlv.socialappapi.service.AccountService;
import com.minhlv.socialappapi.utils.APIResult;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/account")
@Api(tags = "accounts")
@CrossOrigin()
public class AccountController {

	private final ModelMapper modelMapper = new ModelMapper();

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(value = "/")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${AccountController.get-accounts}", response = UserResponseDTO.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult getAccount() {
		return accountService.getAccount();
	}

	@PostMapping(value = "/avatar")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${AccountController.update-avatar}", response = UserResponseDTO.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult updateAvatar(@RequestParam("image") MultipartFile multipartFile) {
		return accountService.updateAvatar(multipartFile);
	}

	@PostMapping(value = "/cover")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "${AccountController.update-cover}", response = UserResponseDTO.class, authorizations = {
			@Authorization(value = "jwt")})
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public APIResult updateCover(@RequestParam("image") MultipartFile multipartFile) {
		return accountService.updateCoverImg(multipartFile);
	}
}
