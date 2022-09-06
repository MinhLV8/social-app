package com.minhlv.socialappapi.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minhlv.socialappapi.entity.SystemCommentsEntity;
import com.minhlv.socialappapi.service.CommentsService;
import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;

@Service
public class CommentsImpl implements CommentsService {

    @Override
    public @NonNull SystemCommentsEntity find(long id, AuthContext authContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @NonNull List<SystemCommentsEntity> list(AuthContext authContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @NonNull SystemCommentsEntity save(@NonNull SystemCommentsEntity payload, @NonNull AuthContext authContext)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @NonNull SystemCommentsEntity update(@NonNull SystemCommentsEntity payload,
            @NonNull AuthContext authContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(@NonNull long[] ids, @NonNull AuthContext authContext) {
        // TODO Auto-generated method stub

    }

    @Override
    public SystemCommentsEntity reply(SystemCommentsEntity reply) {
        // TODO Auto-generated method stub
        return null;
    }

}
