package com.minhlv.socialappapi.service;

import java.io.IOException;
import java.util.List;

import com.minhlv.socialappapi.utils.AuthContext;

import lombok.NonNull;

public interface BaseService<T, P> {

    @NonNull
    T find(long id, final AuthContext authContext);

    @NonNull
    List<T> list(final AuthContext authContext);

    @NonNull
    T save(@NonNull P payload, @NonNull AuthContext authContext) throws IOException;

    @NonNull
    T update(@NonNull P payload, @NonNull AuthContext authContext);

    void delete(@NonNull long[] ids, @NonNull AuthContext authContext);

}
