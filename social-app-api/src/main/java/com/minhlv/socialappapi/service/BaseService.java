package com.minhlv.socialappapi.service;

import org.springframework.http.HttpStatus;

import com.minhlv.socialappapi.exception.CustomException;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;

public interface BaseService<T, P> {

    @NonNull
    T find(@NonNull long id, @NonNull final AuthContext authContext);

    @NonNull
    T list(@NonNull final AuthContext authContext);

    @NonNull
    T save(@NonNull P payload, @NonNull AuthContext authContext);

    @NonNull
    T update(@NonNull P payload, @NonNull AuthContext authContext);

    T delete(@NonNull long[] ids, @NonNull AuthContext authContext);

    public default void checkAllowedFor(final long targetUserId, @NonNull final AuthContext authContext)
            throws CustomException {
        if (targetUserId != authContext.getCurrentAccount().getId()) {
            throw new CustomException(APIResult.MSG.ACTION_FORBIDDEN.getMSG(), HttpStatus.FORBIDDEN);
        }
    }
}
