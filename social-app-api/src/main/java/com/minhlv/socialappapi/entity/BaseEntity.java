package com.minhlv.socialappapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -266794152498589987L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "createdDate")
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date createdDate;

	@Column(name = "modifiedDate")
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date modifiedDate;

	@Column(name = "createdBy")
	@CreatedBy
	private String createdBy;

	@Column(name = "modifiedBy")
	@LastModifiedBy
	private String modifiedBy;

}
