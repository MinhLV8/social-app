package com.minhlv.socialappapi.dto.requestdto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageReponseDTO {

    private long id;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String fileName;
    private long sizeFile;
    private String typeFile;
    private String pathFile;

}
