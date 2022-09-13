package com.minhlv.socialappapi.dto.requestdto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreatePostDTO {
    private List<MultipartFile> images;
    private String caption;
    private String privacy;
}
