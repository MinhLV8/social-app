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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "system_group", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "group_code"))
public class SystemGroup {
    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "vn.unitech.dyndata.util.IDGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "is_enable")
    private Short isEnable = 1;

    @Column(name = "group_code", length = 50)
    private String groupCode;

    // @JsonIgnore
    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_usergroup", schema = "public", joinColumns = {
            @JoinColumn(name = "groupfkid", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "userfkid", nullable = false, updatable = false)})
    private Set<SystemUser> systemUsers = new HashSet<SystemUser>(0);

    // @JsonIgnore
    // @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_grouppermission", schema = "public", joinColumns = {
            @JoinColumn(name = "groupfkid", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "permissionfkid", nullable = false, updatable = false)})
    private Set<SystemPermission> systemPermissions = new HashSet<SystemPermission>(0);

    public SystemGroup() {
    }

    public SystemGroup(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Short getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Short isEnable) {
        this.isEnable = isEnable;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Set<SystemUser> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(Set<SystemUser> systemUsers) {
        this.systemUsers = systemUsers;
    }

    public Set<SystemPermission> getSystemPermissions() {
        return systemPermissions;
    }

    public void setSystemPermissions(Set<SystemPermission> systemPermissions) {
        this.systemPermissions = systemPermissions;
    }

}
