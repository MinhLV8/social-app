package com.minhlv.socialappapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_comments")
@Document(indexName = "system_comments")
public class SystemCommentsEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5720130349551352752L;

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@Column(name = "comment")
	private String comment;

	@Column(name = "times")
	private long times;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "post_id", nullable = false)
	private PostEntity post;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private AccountEntity account;

}
