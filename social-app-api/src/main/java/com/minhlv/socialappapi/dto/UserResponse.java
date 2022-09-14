package com.minhlv.socialappapi.dto;

import java.util.List;

import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.entity.SystemRoleEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponse {

    @ApiModelProperty(position = 3)
    private List<SystemRoleEntity> roles;
    @ApiModelProperty(position = 0)
    private Long id;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String email;
    private AccountEntity accountEntity;
}
