package com.minhlv.socialappapi.dto.requestdto;

import com.minhlv.socialappapi.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreatePostDTO {
	private Set<ImageEntity> images;
	private String caption;
	private String privacy;
}
