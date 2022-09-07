package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.entity.FollowEntity;
import com.minhlv.socialappapi.utils.APIResult;

public interface FollowService extends BaseService<FollowEntity, FollowEntity> {

    APIResult follow(long id);

    APIResult unFollow(long id);

}
