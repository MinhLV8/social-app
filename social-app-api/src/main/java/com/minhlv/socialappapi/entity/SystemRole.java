package com.minhlv.socialappapi.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "system_role", schema = "public")
public class SystemRole {
	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", 
	strategy = "vn.unitech.dyndata.util.IDGenerator")
	private Long id;

	@Column(name = "role", length = 25)
	private String role;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "system_userrole", schema = "public", joinColumns = {
			@JoinColumn(name = "rolefkid", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "userfkid", nullable = false, updatable = false) })
	private Set<SystemUser> systemUsers = new HashSet<SystemUser>(0);

	public SystemRole(Long id, String role, Set<SystemUser> systemUsers) {
		this.id = id;
		this.role = role;
		this.systemUsers = systemUsers;
	}

	public SystemRole() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(Set<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}
	
	
}
