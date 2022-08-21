package com.minhlv.socialappapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_account")
@Document(indexName = "system_account")
public class AccountEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8938047413938216839L;

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(
			name = "system_post_account",
			joinColumns = {@JoinColumn(name = "account_id", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "post_id", nullable = false, updatable = false, insertable = false)}
	)
	Set<PostEntity> posts = new HashSet<>();
	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@NotNull
	@NotBlank
	@Column(name = "surname", length = 10)
	private String surname;

	@NotNull
	@NotBlank
	@Column(name = "firstname", length = 10)
	private String firstName;

	@Column(name = "avatar", columnDefinition = "TEXT")
	private String userAvatar;

	@Column(name = "cover_image", columnDefinition = "TEXT")
	private String userCover;

	@Column(name = "bio")
	private String bio;

	@Column(name = "works_at")
	private String worksAt;

	@Column(name = "lives_in")
	private String livesIn;

	@Column(name = "home_town")
	private String homeTown;

	@Column(name = "relationship_status")
	private short relationshipStatus;

	@Column(name = "last_seen")
	private Date lastSeen;

	@Column(name = "note")
	private String note;

	@NotNull
	@Column(name = "fullname", length = 50)
	private String fullName /* = surname.concat(firstName) */;

	@NotNull
	@Column(name = "is_enable")
	private short isEnable = 1;

	@NotNull
	@Column(name = "is_delete")
	private short isDelete;

	@Column(name = "is_quan_tri", nullable = false)
	private Boolean isQuanTri = false;

	@Column(name = "is_root", nullable = false)
	private Boolean isRoot = false;

	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private SystemUserEntity users;

	@JsonIgnore
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private SystemCommentsEntity comment;

	/*@Transient
	public String getAvatarImagePath() {
		if (userAvatar == null || id == null) return null;
		return "D:/FE/social-app/social-app-api/src/main/resources/user-photos/" + id + "/" + userAvatar;
	}

	@Transient
	public String getCoverImagePath() {
		if (userCover == null || id == null) return null;

		return "D:/FE/social-app/social-app-api/src/main/resources/user-photos/" + id + "/" + userCover;
	}*/
}
