package com.minhlv.socialappapi.service.impl;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.dto.requestdto.AccountUpdateDTO;
import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.entity.ImageEntity;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.repository.AccountRepository;
import com.minhlv.socialappapi.repository.ImageRepository;
import com.minhlv.socialappapi.repository.PostRepository;
import com.minhlv.socialappapi.repository.UserRepository;
import com.minhlv.socialappapi.service.AccountService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;
import com.minhlv.socialappapi.utils.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final PostRepository postRepository;

    private final AccountRepository accountRepository;

    private AuthContext authContext = new AuthContext();

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, ImageRepository imageRepository,
            PostRepository postRepository, AccountRepository accountRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
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
            re.setMessage("Lỗi");
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
            re.setErrorCode(99);
            re.setMessage("Có lỗi xảy ra, thử lại sau.");
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

    @Override
    public APIResult updateAccount(AccountEntity account) {
        APIResult re = new APIResult();
        try {
            Optional<AccountEntity> account = accountRepository.findById(payload.getId());
            if (!account.isPresent()) {
                re.setErrorCode(99);
                re.setMessage("Có lỗi xảy ra, thử lại sau.");
                return re;
            }
            account.get();

            re.setData(null);
            re.setMessage("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            re.setErrorCode(99);
            re.setMessage("Có lỗi xảy ra, thử lại sau.");
        }
        return re;
    }
}
