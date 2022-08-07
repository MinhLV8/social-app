package com.minhlv.socialappapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_account")
public class AccountEntity extends BaseEntity {

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
	@Column(name = "first_name", length = 10)
	private String firstName;

	@Column(name = "lastSeen")
	private Date lastSeen;

	@Column(name = "note")
	private String note;

	@NotNull
	@Column(name = "fullName", length = 50)
	private String fullName /*= surname.concat(firstName)*/;

	@Column(name = "email", length = 100)
	private String email;

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
