package com.minhlv.socialappapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "system_group", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "group_code"))
public class SystemGroupEntity implements Serializable {

	private static final long serialVersionUID = -473760650709037196L;
	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "note")
	private String note;

	@Column(name = "is_enable")
	private Short isEnable = 1;

	@NotNull
	@Column(name = "group_code", length = 50)
	private String groupCode;

	// @JsonIgnore
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "system_usergroup", schema = "public", joinColumns = {
			@JoinColumn(name = "group_id", nullable = false, updatable =
					false)}, inverseJoinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false)})
	private Set<SystemUserEntity> users = new HashSet<>(0);

	// @JsonIgnore
	// @JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "system_grouppermission", schema = "public", joinColumns = {
			@JoinColumn(name = "group_id", nullable = false, updatable =
					false)}, inverseJoinColumns = {
			@JoinColumn(name = "permission_id", nullable = false, updatable =
					false)})
	private Set<SystemPermissionEntity> permissions = new HashSet<>(0);

}
