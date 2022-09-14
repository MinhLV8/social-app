package com.minhlv.socialappapi.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.minhlv.socialappapi.entity.ImageEntity;
import com.minhlv.socialappapi.entity.SystemCommentsEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReponse {

    private long id;

    private String caption;

    @NotNull
    private short privacy;

    @NotNull
    private long likes;

    private long shares;

    private short liked;

    private long times = new Date().getTime();

    @NotNull
    private short isDelete;

    private Set<ImageEntity> images = new HashSet<>();

    private Set<SystemCommentsEntity> comments = new HashSet<>();
}
