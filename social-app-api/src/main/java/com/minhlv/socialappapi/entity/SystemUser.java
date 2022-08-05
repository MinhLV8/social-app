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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "system_user", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class SystemUser {
    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "vn.unitech.dyndata.util.IDGenerator")
    private Long id;
    @Column(name = "password")
    private String password;
    @Column(name = "username", unique = true, length = 50)
    private String username;

    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Account account;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_usergroup", schema = "public", joinColumns = {
            @JoinColumn(name = "userfkid", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "groupfkid", nullable = false, updatable = false, insertable = false)})
    private Set<SystemGroup> systemGroups = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_userrole", schema = "public", joinColumns = {
            @JoinColumn(name = "userfkid", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "rolefkid", nullable = false, updatable = false)})
    private Set<SystemRole> systemRoles = new HashSet<SystemRole>(0);

    public SystemUser() {
    }

    public SystemUser(Long id, String password, String username, Account account, Set<SystemGroup> systemGroups, Set<SystemRole> systemRoles) {
        super();
        this.id = id;
        this.password = password;
        this.username = username;
        this.account = account;
        this.systemGroups = systemGroups;
        this.systemRoles = systemRoles;
    }

    public SystemUser(Long id, String password, String username, Set<SystemGroup> systemGroups, Set<SystemRole> systemRoles) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.systemGroups = systemGroups;
        this.systemRoles = systemRoles;
    }

    public SystemUser(Long id, String username, String password) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public SystemUser(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<SystemGroup> getSystemGroups() {
        return systemGroups;
    }

    public void setSystemGroups(Set<SystemGroup> systemGroups) {
        this.systemGroups = systemGroups;
    }

    public Set<SystemRole> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(Set<SystemRole> systemRoles) {
        this.systemRoles = systemRoles;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
