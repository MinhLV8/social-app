package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;

public interface BaseService<T, P> {

    @NonNull
    T find(long id, final AuthContext authContext);

    @NonNull
    T list(final AuthContext authContext);

    @NonNull
    T save(@NonNull P payload, @NonNull AuthContext authContext);

    @NonNull
    T update(@NonNull P payload, @NonNull AuthContext authContext);

    T delete(@NonNull long[] ids, @NonNull AuthContext authContext);

}
