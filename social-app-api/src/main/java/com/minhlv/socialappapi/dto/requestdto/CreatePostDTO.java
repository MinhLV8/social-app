package com.minhlv.socialappapi.dto.requestdto;

import com.minhlv.socialappapi.entity.ImageEntity;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreatePostDTO {
	Set<ImageEntity> images;
	private String caption;
	private String privacy;
}
