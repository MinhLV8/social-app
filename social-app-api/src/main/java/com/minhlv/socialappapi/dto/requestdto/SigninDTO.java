package com.minhlv.socialappapi.dto.requestdto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDTO {

    @NonNull
    private String username;

    @NotNull
    private String password;

}
