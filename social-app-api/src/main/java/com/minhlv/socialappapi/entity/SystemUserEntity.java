package com.minhlv.socialappapi.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "system_user", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class SystemUserEntity implements Serializable {

    private static final long serialVersionUID = -510681648851438653L;
    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
    private Long id;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 120)
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "sdt", nullable = false)
    @NotBlank
    @Size(max = 22)
    private String sdt;

    // @JsonIgnore
    // @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountEntity accountEntity;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_usergroup", schema = "public", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "group_id", nullable = false, updatable = false, insertable = false)})
    private Set<SystemGroupEntity> groups = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_userrole", schema = "public", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "role_id", nullable = false, updatable = false)})
    private Set<SystemRoleEntity> roles = new HashSet<>();

}
