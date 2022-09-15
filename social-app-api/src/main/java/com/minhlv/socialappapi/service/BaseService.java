package com.minhlv.socialappapi.service;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import com.minhlv.socialappapi.exception.CustomException;
import com.minhlv.socialappapi.utils.APIResult;
import com.minhlv.socialappapi.utils.AuthContext;

public interface BaseService<T, P> {

    @NotNull
    T find(@NotNull long id, @NotNull final AuthContext authContext);

    @NotNull
    T list(@NotNull final AuthContext authContext);

    @NotNull
    T save(@NotNull P payload, @NotNull AuthContext authContext);

    @NotNull
    T update(@NotNull P payload, @NotNull AuthContext authContext);

    T delete(@NotNull long[] ids, @NotNull AuthContext authContext);

    public default void checkAllowedFor(final long targetUserId, @NotNull final AuthContext authContext)
            throws CustomException {
        if (targetUserId != authContext.getCurrentAccount().getId()) {
            throw new CustomException(APIResult.MSG.ACTION_FORBIDDEN.getMSG(), HttpStatus.FORBIDDEN);
        }
    }
}
