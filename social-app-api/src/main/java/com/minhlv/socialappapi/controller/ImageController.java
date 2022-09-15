package com.minhlv.socialappapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.service.ImageService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/image")
@Api(tags = "images")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    // private final ModelMapper modelMapper = new ModelMapper();

    private final AuthContext authContext;

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService, AuthContext authContext) {
        this.imageService = imageService;
        this.authContext = authContext;
    }

    @SuppressWarnings("unused")
    private void setDispositionHeader(HttpServletResponse response, String fileName, String fileType) {
        response.setHeader("Content-Type", fileType);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("accept-ranges", "bytes");
        response.addHeader("fileName", fileName);
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
    public APIResult saveImage(@RequestBody List<MultipartFile> multipartFiles) {
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
