package com.minhlv.socialappapi.service.impl;

import java.io.IOException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.dto.requestdto.AccountUpdateDTO;
import com.minhlv.socialappapi.entity.ImageEntity;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.repository.ImageRepository;
import com.minhlv.socialappapi.repository.PostRepository;
import com.minhlv.socialappapi.repository.UserRepository;
import com.minhlv.socialappapi.service.AccountService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final PostRepository postRepository;

    public AccountServiceImpl(UserRepository userRepository, ImageRepository imageRepository,
            PostRepository postRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public APIResult getAccount() {
        APIResult re = new APIResult();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SystemUserEntity user = userRepository.findByUsername(username);
        re.setData(user.getAccountEntity());
        re.setMessage("Thành công");
        return re;
    }

    @Override
    @Transactional
    public APIResult updateAvatar(MultipartFile multipartFile) {
        APIResult re = new APIResult();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SystemUserEntity user = userRepository.findByUsername(username);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            user.getAccountEntity().setUserAvatar(FileUploadUtil.compressImage(multipartFile.getBytes()));
            user.getAccountEntity().setUserAvatarContentType(multipartFile.getContentType());
            userRepository.save(user);

            PostEntity postChangeAvatar = new PostEntity();
            postChangeAvatar.setCaption("Thay đổi ảnh đại diện.");
            postChangeAvatar.setPrivacy((short) 1);
            postChangeAvatar.getAccounts().add(user.getAccountEntity());

            String avtPath = FileUploadUtil.saveFile("uploads/photos/" + username, fileName, multipartFile);

            ImageEntity avatar = imageRepository.save(ImageEntity.builder().fileName(fileName)
                    .typeFile(multipartFile.getContentType()).pathFile(avtPath).sizeFile(multipartFile.getSize())
                    .image(FileUploadUtil.compressImage(multipartFile.getBytes()))
                    .post(postRepository.save(postChangeAvatar)).build());
            re.setData(avatar);
            re.setMessage("Thành công");
        } catch (IOException e) {
            re.setMessage("Lỗi.");
        }
        return re;
    }

    @Override
    public APIResult updateFullName(AccountUpdateDTO payload) {
        return null;
    }

    @Override
    public APIResult updateCoverImg(MultipartFile multipartFile) {
        APIResult re = new APIResult();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SystemUserEntity user = userRepository.findByUsername(username);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            user.getAccountEntity().setUserCover(FileUploadUtil.compressImage(multipartFile.getBytes()));
            user.getAccountEntity().setUserCoverContentType(multipartFile.getContentType());
            userRepository.save(user);

            PostEntity postChangeCover = new PostEntity();
            postChangeCover.setCaption("Thay đổi ảnh bìa.");
            postChangeCover.setPrivacy((short) 1);
            postChangeCover.getAccounts().add(user.getAccountEntity());
            String avtPath = FileUploadUtil.saveFile("uploads/photos/" + username, fileName, multipartFile);

            imageRepository.save(ImageEntity.builder().fileName(fileName).typeFile(multipartFile.getContentType())
                    .pathFile(avtPath).sizeFile(multipartFile.getSize())
                    .image(FileUploadUtil.compressImage(multipartFile.getBytes()))
                    .post(postRepository.save(postChangeCover)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        re.setData(fileName);
        re.setMessage("Thành công");
        return re;
    }

    public PostEntity changeAvatarPost() {

        return new PostEntity();
    }

    public PostEntity changeCoverPost() {

        return new PostEntity();
    }
}
