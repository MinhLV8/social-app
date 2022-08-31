package com.minhlv.socialappapi.dto.requestdto;

import java.util.Set;

import com.minhlv.socialappapi.entity.ImageEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreatePostDTO {
    private Set<ImageEntity> images;
    private String caption;
    private String privacy;
}
