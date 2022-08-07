package com.minhlv.socialappapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_account")
public class AccountEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8938047413938216839L;
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

	@Column(name = "last_seen")
	private Date lastSeen;

	@Column(name = "note")
	private String note;

	@NotNull
	@Column(name = "fullname", length = 50)
	private String fullName /*= surname.concat(firstName)*/;

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
	@JoinColumn(name = "user_id")
	private SystemUserEntity users;
}
