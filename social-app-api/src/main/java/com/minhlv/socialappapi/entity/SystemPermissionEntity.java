package com.minhlv.socialappapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "system_permission")
public class SystemPermissionEntity implements Serializable {

	private static final long serialVersionUID = -3332282666145514411L;
	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "permission")
	private String permission;

	@Column(name = "note")
	private String note;

	@Column(name = "is_enable")
	private Short isEnable;

	@Column(name = "sort")
	private Short sort;

	@Column(name = "id_parent")
	private Long idParent;

	@Column(name = "url")
	private String url;

	@Column(name = "type")
	private Integer type;

	@Column(name = "icon")
	private String icon;

	@Column(name = "menu_top")
	private boolean menuTop;

	@Column(name = "invisible")
	private boolean invisible = true;

	@Column(name = "id_chuc_nang", length = 255)
	private String idChucNang;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "system_grouppermission", schema = "public", joinColumns = {
			@JoinColumn(name = "permission_id", nullable = false, updatable =
					false)}, inverseJoinColumns = {
			@JoinColumn(name = "group_id", nullable = false, updatable =
					false)})
	private Set<SystemGroupEntity> groups = new HashSet<>(0);
}
