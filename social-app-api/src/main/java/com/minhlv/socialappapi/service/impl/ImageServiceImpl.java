package com.minhlv.socialappapi.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.dto.requestdto.ImageReponseDTO;
import com.minhlv.socialappapi.entity.ImageEntity;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.exception.CustomException;
import com.minhlv.socialappapi.repository.ImageRepository;
import com.minhlv.socialappapi.service.ImageService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.APIResult.MSG;
import com.minhlv.socialappapi.utils.AuthContext;
import com.minhlv.socialappapi.utils.FileUploadUtil;

import lombok.NonNull;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final Path fileStorageLocation;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.fileStorageLocation = Paths.get("/uploads/photos/minhlv").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new CustomException("Could not create the directory where the uploaded files will be stored.",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public APIResult find(@NotNull long id, @NonNull AuthContext authContext) {
        APIResult result = new APIResult();
        try {
            Optional<ImageEntity> image = imageRepository.findById(id);

            if (image.isPresent()) {
                result.setData(image.get(), MSG.SUCCESS);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
            return result;
        }
        return result;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        APIResult result = new APIResult();
        try {
            ImageEntity image = imageRepository.findByFileName(fileName);
            if (image == null) {
                return null;
            }
            return FileUploadUtil.getFile(image.getPathFile());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
            return null;
        }
    }

    @Override
    public APIResult list(@NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public APIResult save(@NonNull ImageEntity payload, @NonNull AuthContext authContext) {
        APIResult result = new APIResult();
        try {
            ImageEntity newImage = imageRepository.save(payload);
            result.setData(newImage, APIResult.MSG.SUCCESS);
            return result;
        } catch (Exception e) {
            result.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
            return result;
        }
    }

    @Override
    public APIResult update(@NonNull ImageEntity payload, @NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public APIResult delete(@NonNull long[] ids, @NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public APIResult save(List<MultipartFile> multipartFiles, AuthContext authContext) {
        APIResult result = new APIResult();
        try {
            List<ImageReponseDTO> newImages = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                String imgPost = FileUploadUtil.saveFile("uploads/photos/" + authContext.getUsername() + "/postImages/",
                        fileName, multipartFile);
                ImageEntity newImage = imageRepository.save(ImageEntity.builder().fileName(fileName)
                        .typeFile(multipartFile.getContentType()).pathFile(imgPost).sizeFile(multipartFile.getSize())
                        .image(Base64.getEncoder().encode(FileUploadUtil.compressImage(multipartFile.getBytes())))
                        .build());
                newImages.add(modelMapper.map(newImage, ImageReponseDTO.class));
            }
            result.setData(newImages, APIResult.MSG.SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
            return result;
        }
    }

    @Override
    public APIResult save(List<MultipartFile> multipartFiles, PostEntity post, AuthContext authContext) {
        APIResult result = new APIResult();
        try {
            List<ImageReponseDTO> newImages = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                String imgPost = FileUploadUtil.saveFile("uploads/photos/" + authContext.getUsername() + "/postImages/",
                        fileName, multipartFile);
                ImageEntity newImage = imageRepository.save(ImageEntity.builder().fileName(fileName)
                        .typeFile(multipartFile.getContentType()).pathFile(imgPost).sizeFile(multipartFile.getSize())
                        .image(FileUploadUtil.compressImage(multipartFile.getBytes())).post(post).build());
                newImages.add(modelMapper.map(newImage, ImageReponseDTO.class));
            }
            result.setData(newImages, APIResult.MSG.SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
            return result;
        }
    }

}
