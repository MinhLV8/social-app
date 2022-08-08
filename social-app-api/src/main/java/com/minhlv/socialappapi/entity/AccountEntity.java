package com.minhlv.socialappapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_account")
@Document(indexName = "system_account")
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
    private String fullName /* = surname.concat(firstName) */;

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
