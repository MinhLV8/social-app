package com.minhlv.socialappapi.service.impl;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
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
        re.setData(user.getAccountEntity(), APIResult.MSG.SUCCESS);
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
            postChangeAvatar.setCaption(user.getAccountEntity().getFullName() + " đã thay đổi ảnh đại diện.");
            postChangeAvatar.setPrivacy((short) 1);
            postChangeAvatar.setAccount(user.getAccountEntity());

            String avtPath = FileUploadUtil.saveFile("uploads/photos/" + username, fileName, multipartFile);

            imageRepository.save(ImageEntity.builder().fileName(fileName).typeFile(multipartFile.getContentType())
                    .pathFile(avtPath).sizeFile(multipartFile.getSize())
                    .image(FileUploadUtil.compressImage(multipartFile.getBytes()))
                    .post(postRepository.save(postChangeAvatar)).build());
            re.setData(fileName, APIResult.MSG.SUCCESS);
        } catch (IOException e) {
            re.setMessage(e);
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
            postChangeCover.setCaption(user.getAccountEntity().getFullName() + "đã thay đổi ảnh bìa.");
            postChangeCover.setPrivacy((short) 1);
            postChangeCover.setAccount(user.getAccountEntity());
            String avtPath = FileUploadUtil.saveFile("uploads/photos/" + username, fileName, multipartFile);

            imageRepository.save(ImageEntity.builder().fileName(fileName).typeFile(multipartFile.getContentType())
                    .pathFile(avtPath).sizeFile(multipartFile.getSize())
                    .image(FileUploadUtil.compressImage(multipartFile.getBytes()))
                    .post(postRepository.save(postChangeCover)).build());
        } catch (IOException e) {
            re.setMessage(e);
        }
        re.setData(fileName, APIResult.MSG.SUCCESS);
        return re;
    }

    @Override
    public APIResult updateAccount(AccountEntity account) {
        APIResult re = new APIResult();
        try {
            Optional<AccountEntity> accountUpdate = accountRepository.findById(account.getId());
            if (!accountUpdate.isPresent()) {
                re.setMessage(500, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED.getMSG());
                return re;
            }
            if (!Objects.equals(account.getId(), authContext.getCurrentAccount().getId())) {
                re.setMessage(403, APIResult.MSG.ACTION_FORBIDDEN);
                return re;
            }
            AccountEntity accountExist = accountUpdate.get();
            accountExist.setSurName(account.getSurName());
            accountExist.setFirstName(account.getFirstName());
            accountExist.setWorksAt(account.getWorksAt());
            accountExist.setSex(account.getSex());
            accountExist.setLivesIn(account.getLivesIn());
            accountExist.setHomeTown(account.getHomeTown());
            accountExist.setDateOfBirth(account.getDateOfBirth());
            accountExist.setRelationshipStatus(account.getRelationshipStatus());
            accountExist.setBio(account.getBio());
            accountRepository.save(accountExist);
            re.setData(new ModelMapper().map(accountExist, AccountUpdateDTO.class), APIResult.MSG.SUCCESS);
        } catch (Exception e) {
            re.setMessage(e);
        }
        return re;
    }

    public PostEntity changeAvatarPost() {

        return new PostEntity();
    }

    public PostEntity changeCoverPost() {

        return new PostEntity();
    }

}
