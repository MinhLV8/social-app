package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.entity.SystemCommentsEntity;

import lombok.NonNull;

public interface CommentsService extends BaseService<SystemCommentsEntity, SystemCommentsEntity> {

    @NonNull
    SystemCommentsEntity reply(@NonNull SystemCommentsEntity reply);

}
