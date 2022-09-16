package com.minhlv.socialappapi.service;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;

import com.minhlv.socialappapi.exception.CustomException;
import com.minhlv.socialappapi.exception.HandledException;
import com.minhlv.socialappapi.utils.APIResult.MSG;
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

    default void checkAllowedFor(final long targetUserId, @NotNull final AuthContext authContext)
            throws CustomException {
        if (targetUserId != authContext.getCurrentAccount().getId()) {
            throw new CustomException(MSG.ACTION_FORBIDDEN.getMSG(), HttpStatus.FORBIDDEN);
        }
    }

    default void checkExist(Class<T> neededClass) throws HandledException {
        if (ObjectUtils.isNotEmpty(neededClass)) {
            return;
        }
        throw new HandledException(404, MSG.NOT_EXISTS.getMSG());
    }

    default void checkAllowed(final long targetId, AuthContext authContext) throws HandledException {
        if (Boolean.TRUE.equals(authContext.getCurrentAccount().getIsRoot())) {
            return;
        }
        if (targetId == authContext.getCurrentUser().getId()) {
            return;
        }
        throw new HandledException(403, MSG.ACTION_FORBIDDEN.getMSG());
    }
}
