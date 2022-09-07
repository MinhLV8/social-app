package com.minhlv.socialappapi.service.impl;

import java.io.IOException;
import java.util.List;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @NonNull List<FollowEntity> list(AuthContext authContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @NonNull FollowEntity save(@NonNull FollowEntity payload, @NonNull AuthContext authContext)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @NonNull FollowEntity update(@NonNull FollowEntity payload, @NonNull AuthContext authContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(@NonNull long[] ids, @NonNull AuthContext authContext) {
        // TODO Auto-generated method stub

    }

    @Override
    public APIResult follow(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public APIResult unFollow(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
