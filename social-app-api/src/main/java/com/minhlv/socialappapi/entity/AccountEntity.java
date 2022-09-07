package com.minhlv.socialappapi.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_account")
@Document(indexName = "system_account")
public class AccountEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8938047413938216839L;

    @JsonBackReference
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<PostEntity> posts = new HashSet<>();

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

    @NotNull
    @Column(name = "sex")
    private int sex; /*-1 0 1*/

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "avatar", columnDefinition = "bytea")
    private byte[] userAvatar;

    @Column(name = "avatar_content_type")
    private String userAvatarContentType;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "cover", columnDefinition = "bytea")
    private byte[] userCover;

    @Column(name = "cover_content_type")
    private String userCoverContentType;

    @Column(name = "bio")
    private String bio;

    @Column(name = "works_at")
    private String worksAt;

    @Column(name = "lives_in")
    private String livesIn;

    @Column(name = "home_town")
    private String homeTown;

    @Column(name = "relationship_status")
    private short relationshipStatus;

    @Column(name = "last_seen")
    private Date lastSeen;

    @Column(name = "note")
    private String note;

    @NotNull
    @NotBlank
    @Column(name = "fullname", length = 50)
    private String fullName;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "is_enable")
    private short isEnable = 1;

    @NotNull
    @Column(name = "is_delete")
    private short isDelete = 0;

    @Column(name = "is_quan_tri", nullable = false)
    private Boolean isQuanTri = false;

    @Column(name = "is_root", nullable = false)
    private Boolean isRoot = false;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private SystemUserEntity users;

    @JsonIgnore
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private SystemCommentsEntity comment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_account_followers", schema = "public", joinColumns = {
            @JoinColumn(name = "account_id", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "folower_id", nullable = false, updatable = false)})
    private Set<AccountEntity> followers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_account_followings", schema = "public", joinColumns = {
            @JoinColumn(name = "account_id", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "folowings_id", nullable = false, updatable = false)})
    private Set<AccountEntity> followings = new HashSet<>();
}
