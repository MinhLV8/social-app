package com.minhlv.socialappapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode(exclude = "dependent_list")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_post")
@Document(indexName = "system_post")
public class PostEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5636313057165892526L;

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@Column(name = "caption", columnDefinition = "TEXT")
	private String caption;

	@NotNull
	@Column(name = "privacy")
	private short privacy;

	@NotNull
	@Column(name = "likes")
	private long likes = 0L;

	@Column(name = "shares")
	private long shares = 0L;

	@Column(name = "liked")
	private short liked = 0;

	@Column(name = "times")
	private long times = new Date().getTime();

	@NotNull
	@Column(name = "is_delete")
	private short isDelete = 0;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "system_post_account", joinColumns = {
			@JoinColumn(name = "post_id", nullable = false, updatable =
					false)}, inverseJoinColumns = {
			@JoinColumn(name = "account_id", nullable = false, updatable = false)})
	private Set<AccountEntity> accounts = new HashSet<>();

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ImageEntity> images;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<SystemCommentsEntity> comments;


	public PostEntity(String caption, @NotNull short privacy, Set<AccountEntity> accounts, Set<ImageEntity> images) {
		this.caption = caption;
		this.privacy = privacy;
		this.accounts = accounts;
		this.images = images;
	}
}
