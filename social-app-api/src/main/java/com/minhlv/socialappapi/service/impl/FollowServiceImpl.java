package com.minhlv.socialappapi.service.impl;

import org.springframework.stereotype.Service;

import com.minhlv.socialappapi.entity.FollowEntity;
import com.minhlv.socialappapi.service.FollowService;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;

@Service
public class FollowServiceImpl implements FollowService {

    @Override
    public @NonNull FollowEntity find(long id, AuthContext authContext) {
        return null;
    }

    @Override
    public @NonNull FollowEntity list(AuthContext authContext) {
        return null;
    }

    @Override
    public @NonNull FollowEntity save(@NonNull FollowEntity payload, @NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public @NonNull FollowEntity update(@NonNull FollowEntity payload, @NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public FollowEntity delete(@NonNull long[] ids, @NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public APIResult follow(long id) {
        return null;
    }

    @Override
    public APIResult unFollow(long id) {
        return null;
    }
}
