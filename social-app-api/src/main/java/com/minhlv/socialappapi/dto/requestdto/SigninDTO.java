package com.minhlv.socialappapi.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDTO {

    @NonNull()
    private String username;

    @NotNull
    private String password;
}
