package com.minhlv.socialappapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "system_role", schema = "public")
public class SystemRoleEntity {
	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid",
			strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@NotNull
	@Column(name = "role", length = 25)
	private String role;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "system_userrole", schema = "public", joinColumns = {
			@JoinColumn(name = "role_id", nullable = false, updatable = false)}, inverseJoinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false)})
	private Set<SystemUserEntity> systemUserEntities = new HashSet<>(0);

}
