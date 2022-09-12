package com.minhlv.socialappapi.service.impl;

import org.springframework.stereotype.Service;

import com.minhlv.socialappapi.entity.SystemCommentsEntity;
import com.minhlv.socialappapi.service.CommentsService;
import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;

@Service
public class CommentsImpl implements CommentsService {

    @Override
    public @NonNull SystemCommentsEntity reply(SystemCommentsEntity reply) {
        return new SystemCommentsEntity();
    }

    @Override
    public @NonNull SystemCommentsEntity find(long id, AuthContext authContext) {
        return null;
    }

    @Override
    public @NonNull SystemCommentsEntity list(AuthContext authContext) {
        return null;
    }

    @Override
    public @NonNull SystemCommentsEntity save(@NonNull SystemCommentsEntity payload, @NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public @NonNull SystemCommentsEntity update(@NonNull SystemCommentsEntity payload,
            @NonNull AuthContext authContext) {
        return null;
    }

    @Override
    public SystemCommentsEntity delete(@NonNull long[] ids, @NonNull AuthContext authContext) {
        return null;
    }

}
