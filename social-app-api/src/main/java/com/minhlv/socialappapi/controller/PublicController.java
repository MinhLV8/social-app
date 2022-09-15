package com.minhlv.socialappapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhlv.socialappapi.service.ImageService;
import com.minhlv.socialappapi.utils.APIResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/photo")
public class PublicController {

    private final ImageService imageService;

    @Autowired
    public PublicController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{fileName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${ImageController.getImage}", response = APIResult.class, authorizations = {
            @Authorization(value = "jwt")})
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Resource> getUrlImage(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = imageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;
                // filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
