package com.minhlv.socialappapi.service.impl;

import com.minhlv.socialappapi.entity.ImageEntity;
import com.minhlv.socialappapi.repository.ImageRepository;
import com.minhlv.socialappapi.service.ImageService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;
import com.minhlv.socialappapi.utils.FileUploadUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

	private final ImageRepository imageRepository;

	@Autowired
	public ImageServiceImpl(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public APIResult find(@NonNull long id, @NonNull AuthContext authContext) {
		return null;
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
	public APIResult save(MultipartFile[] multipartFiles, AuthContext authContext) {
		APIResult result = new APIResult();
		try {
			List<ImageEntity> newImages = new ArrayList<>();
			for (MultipartFile multipartFile : multipartFiles) {
				String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
				String imgPost = FileUploadUtil.saveFile("uploads/photos/" + authContext.getUsername() + "/postImages/",
						fileName, multipartFile);
				ImageEntity newImage =
						imageRepository.save(ImageEntity.builder().fileName(fileName).typeFile(multipartFile.getContentType())
								.pathFile(imgPost).sizeFile(multipartFile.getSize())
								.image(FileUploadUtil.compressImage(multipartFile.getBytes())).build());
				newImages.add(newImage);
			}
			result.setData(newImages, APIResult.MSG.SUCCESS);
			return result;
		} catch (Exception e) {
			result.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
			return result;
		}
	}
}
