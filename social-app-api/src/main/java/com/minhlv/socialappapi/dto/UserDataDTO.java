package com.minhlv.socialappapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataDTO {

    @ApiModelProperty(position = 4)
    private String username;
    @ApiModelProperty(position = 1)
    private String email;
    @ApiModelProperty(position = 2)
    private String password;
    private String lastname;
    private String surname;
    private int sex;
    private String sdt;
}
