package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.entity.SystemCommentsEntity;

public interface CommentsService extends BaseService<SystemCommentsEntity, SystemCommentsEntity> {

    SystemCommentsEntity reply(SystemCommentsEntity reply);

}
