package com.minhlv.socialappapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.service.AccountService;
import com.minhlv.socialappapi.utils.APIResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

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
    @ApiOperation(value = "${AccountController.get-accounts}", response = APIResult.class, authorizations = {
            @Authorization(value = "jwt")})
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResult getAccount() {
        return accountService.getAccount();
    }

    @PostMapping(value = "/avatar")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${AccountController.update-avatar}", response = APIResult.class, authorizations = {
            @Authorization(value = "jwt")})
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResult updateAvatar(@RequestParam("image") MultipartFile multipartFile) {
        return accountService.updateAvatar(multipartFile);
    }

    @PostMapping(value = "/cover")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${AccountController.update-cover}", response = APIResult.class, authorizations = {
            @Authorization(value = "jwt")})
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResult updateCover(@RequestParam("image") MultipartFile multipartFile) {
        return accountService.updateCoverImg(multipartFile);
    }
}
