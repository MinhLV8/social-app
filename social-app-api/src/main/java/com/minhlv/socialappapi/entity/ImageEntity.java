package com.minhlv.socialappapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_image")
@ToString
@EntityListeners(AuditingEntityListener.class)
@Document(indexName = "system_image")
public class ImageEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6760750782288099866L;

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@NotNull
	@JsonProperty
	@Column(name = "file_name")
	private String fileName;

	@Column(name = "size_file")
	private long sizeFile;

	@Column(name = "type_file")
	private String typeFile;

	@Column(name = "path_file")
	private String pathFile;

	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "image", columnDefinition = "bytea")
	private byte[] image;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "post_id", nullable = true)
	private PostEntity post;

}
