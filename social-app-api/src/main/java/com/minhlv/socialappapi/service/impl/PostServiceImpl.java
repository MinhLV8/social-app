package com.minhlv.socialappapi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.dto.PostReponse;
import com.minhlv.socialappapi.dto.requestdto.LikePostDTO;
import com.minhlv.socialappapi.dto.requestdto.UpdatePrivacyPostDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.repository.PostRepository;
import com.minhlv.socialappapi.service.ImageService;
import com.minhlv.socialappapi.service.PostService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.APIResult.MSG;
import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ImageService imageService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.imageService = imageService;
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull APIResult find(long id, AuthContext authContext) {
        APIResult re = new APIResult();
        Optional<PostEntity> getPost = postRepository.findById(id);
        if (!getPost.isPresent()) {

            re.setMessage(6, APIResult.MSG.NOT_EXISTS);
            return re;
        }
        re.setData(getPost.get(), APIResult.MSG.SUCCESS);
        return re;
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull APIResult list(AuthContext authContext) {
        APIResult re = new APIResult();
        Iterable<PostEntity> getPosts = postRepository.findAllByAccount(authContext.getCurrentAccount());
        List<PostReponse> posts = new ArrayList<>();
        getPosts.forEach(post -> posts.add(modelMapper.map(post, PostReponse.class)));
        re.setData(posts, APIResult.MSG.SUCCESS);
        return re;
    }

    @Override
    @Transactional
    public @NonNull APIResult save(@NonNull PostEntity payload, @NonNull AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            payload.setAccount(authContext.getCurrentAccount());
            payload.getImages().forEach(item -> item.setPost(payload));
            PostEntity newPost = postRepository.save(payload);
            re.setData(newPost, APIResult.MSG.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    @Transactional
    public APIResult save(PostEntity post, List<MultipartFile> images, AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            post.setAccount(authContext.getCurrentAccount());
            PostEntity newPost = postRepository.save(post);
            imageService.save(images, newPost, authContext);
            // log.info("{}", find(newPost.getId(), authContext).getData());
            PostReponse postReponseDTO = modelMapper.map(find(newPost.getId(), authContext).getData(),
                    PostReponse.class);
            re.setData(postReponseDTO, MSG.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    @Transactional
    public @NonNull APIResult update(@NonNull PostEntity payload, @NonNull AuthContext authContext) {
        APIResult re = new APIResult();
        try {

            Optional<PostEntity> post = postRepository.findById(payload.getId());
            if (!post.isPresent()) {
                return re;
            }
            PostEntity postEntity = post.get();
            checkAllowedFor(postEntity.getAccount().getId(), authContext);

        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    public APIResult delete(@NonNull long[] ids, @NonNull AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            List<PostEntity> listWillDel = new ArrayList<>();
            Arrays.stream(ids).forEach(id -> {
                Optional<PostEntity> postDel = postRepository.findById(id);
                if (!postDel.isPresent()) {
                    listWillDel.add(postDel.get());
                }
            });
            postRepository.deleteAll(listWillDel);
            re.setMessage(0, APIResult.MSG.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    public APIResult likePost(LikePostDTO payload) {

        return null;
    }

    @Override
    public APIResult updatePrivacyPost(UpdatePrivacyPostDTO payload, AuthContext authContext) {

        return null;
    }

}
