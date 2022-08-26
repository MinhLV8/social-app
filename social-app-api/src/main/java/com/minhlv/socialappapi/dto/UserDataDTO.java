package com.minhlv.socialappapi.dto;

import java.util.List;

import com.minhlv.socialappapi.entity.SystemRoleEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataDTO {

    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String email;
    @ApiModelProperty(position = 2)
    private String password;
    private String sdt;
    @ApiModelProperty(position = 3)
    List<SystemRoleEntity> appUserRoles;
}
