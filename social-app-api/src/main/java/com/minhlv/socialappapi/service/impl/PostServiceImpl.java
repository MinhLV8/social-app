package com.minhlv.socialappapi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhlv.socialappapi.dto.PostReponseDTO;
import com.minhlv.socialappapi.dto.requestdto.LikePostDTO;
import com.minhlv.socialappapi.dto.requestdto.UpdatePrivacyPostDTO;
import com.minhlv.socialappapi.entity.PostEntity;
import com.minhlv.socialappapi.exception.CustomException;
import com.minhlv.socialappapi.repository.PostRepository;
import com.minhlv.socialappapi.service.PostService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
        List<PostReponseDTO> posts = new ArrayList<>();
        getPosts.forEach(post -> posts.add(modelMapper.map(post, PostReponseDTO.class)));
        re.setData(posts, APIResult.MSG.SUCCESS);
        return re;
    }

    @Override
    @Transactional
    public @NonNull APIResult save(@NonNull PostEntity payload, @NonNull AuthContext authContext) {
        APIResult re = new APIResult();
        try {
            log.info(" PostEntity payload > {}", payload);
            payload.setAccount(authContext.getCurrentAccount());
            PostEntity newPost = postRepository.save(payload);
            re.setData(newPost, APIResult.MSG.SUCCESS);
        } catch (Exception e) {
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
            re.setMessage(99, APIResult.MSG.UNEXPECTED_ERROR_OCCURRED);
        }
        return re;
    }

    @Override
    public APIResult likePost(LikePostDTO payload) {

        return null;
    }

    @Override
    public APIResult updatePrivacyPost(UpdatePrivacyPostDTO payload) {

        return null;
    }

    private void checkAllowedFor(final long targetUserId, @NonNull final AuthContext authContext)
            throws CustomException {
        if (targetUserId != authContext.getCurrentAccount().getId()) {
            throw new CustomException(APIResult.MSG.ACTION_FORBIDDEN.getMSG(), HttpStatus.FORBIDDEN);
        }
    }

}
