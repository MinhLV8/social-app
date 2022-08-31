package com.minhlv.socialappapi.dto.requestdto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateDTO {
    private long id;
    private String surname;
    private String firstname;
    private String worksAt;
    private int sex;
    private String liveIn;
    private String homeTown;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private short relationshipStatus;
    private String bio;
}
