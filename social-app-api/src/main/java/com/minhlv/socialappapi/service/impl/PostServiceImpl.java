package com.minhlv.socialappapi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.minhlv.socialappapi.dto.PhotoResponse;
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
    public @NotNull APIResult find(long id, AuthContext authContext) {
        APIResult re = new APIResult();
        final Optional<PostEntity> getPost = postRepository.findById(id);
        if (!getPost.isPresent()) {
            re.setMessage(404, MSG.NOT_EXISTS);
            return re;
        }
        PostEntity postEntity = getPost.get();
        PostReponse postReponse = this.toPhotoResponse(postEntity);
        re.setData(postReponse, MSG.SUCCESS);
        return re;
    }

    @Override
    @Transactional(readOnly = true)
    public @NotNull APIResult list(AuthContext authContext) {
        APIResult re = new APIResult();
        Iterable<PostEntity> getPosts = postRepository.findAllByAccount(authContext.getCurrentAccount());
        List<PostReponse> posts = new ArrayList<>();
        getPosts.forEach(post -> posts.add(this.toPhotoResponse(post)));
        re.setData(posts, MSG.SUCCESS);
        return re;
    }

    @Override
    @Transactional(readOnly = true)
    public APIResult timeLine(PostEntity post, List<MultipartFile> images, AuthContext authContext) {

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public APIResult newFeel(PostEntity post, List<MultipartFile> images, AuthContext authContext) {

        return null;
    }

    @Override
    @Transactional
    public @NotNull APIResult save(@NotNull PostEntity payload, @NotNull AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            payload.setAccount(authContext.getCurrentAccount());
            payload.getImages().forEach(item -> item.setPost(payload));
            PostEntity newPost = postRepository.save(payload);
            re.setData(newPost, MSG.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    @Transactional
    public APIResult save(PostEntity post, List<MultipartFile> images, AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            post.setAccount(authContext.getCurrentAccount());
            imageService.save(images, postRepository.save(post), authContext);

            PostReponse postReponse = this.toPhotoResponse(postRepository.findById(post.getId()).get());
            re.setData(postReponse, MSG.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    @Transactional
    public @NotNull APIResult update(@NotNull PostEntity payload, @NotNull AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            final Optional<PostEntity> post = postRepository.findById(payload.getId());
            if (!post.isPresent()) {
                return re;
            }
            PostEntity postEntity = post.get();
            checkAllowedFor(postEntity.getAccount().getId(), authContext);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    public APIResult delete(@NotNull long[] ids, @NotNull AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            List<PostEntity> listWillDel = new ArrayList<>();
            Arrays.stream(ids).forEach(id -> {
                final Optional<PostEntity> postDel = postRepository.findById(id);
                if (!postDel.isPresent()) {
                    listWillDel.add(postDel.get());
                }
            });
            postRepository.deleteAll(listWillDel);
            re.setMessage(0, MSG.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage(99, MSG.UNEXPECTED_ERROR_OCCURRED);
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

    private String genUrlPhoto(String fileName) {
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return baseUrl + "/api/photo/" + fileName;
    }

    private PostReponse toPhotoResponse(PostEntity entity) {
        PostReponse postReponse = modelMapper.map(entity, PostReponse.class);
        Set<PhotoResponse> photos = new HashSet<>();
        entity.getImages().forEach(item -> {
            PhotoResponse photoResponse = new PhotoResponse();
            photoResponse.setId(item.getId());
            photoResponse.setUrl(this.genUrlPhoto(item.getFileName()));
            photos.add(photoResponse);
        });
        postReponse.setImages(photos);
        return postReponse;
    }

}
