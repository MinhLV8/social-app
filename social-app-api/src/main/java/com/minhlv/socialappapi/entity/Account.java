package com.minhlv.socialappapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system_account")
public class Account {

    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "vn.unitech.dyndata.util.IDGenerator")
    private long id;

    @Column(name = "name", unique = true, length = 50)
    private String name;

    @Column(name = "lastSeen")
    private Date lastSeen;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "fullName", length = 255)
    private String fullName;

    @Column(name = "phongban_id")
    private Long phongBanId;

    @Transient
    private String tenphongban;

    @Column(name = "regency", length = 255)
    private String regency;

    @Column(name = "phoneNumber", length = 50)
    private String phoneNumber;

    @Column(name = "desktopPhoneNumber", length = 50)
    private String desktopPhoneNumber;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "isEnable")
    private short isEnable = 1;

    @Column(name = "isDelete")
    private short isDelete;

    @Column(name = "managementDepartment", length = 50)
    private String managementDepartment;

    @Column(name = "sort")
    private short sort;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "memberCreate", length = 100)
    private String memberCreate;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "memberUpdate", length = 100)
    private String memberUpdate;

    @Column(name = "ten_don_vi")
    private String tenDonVi;

    @Column(name = "is_quan_tri", nullable = false)
    private Boolean isQuanTri = false;

    @Column(name = "is_root", nullable = false)
    private Boolean isRoot = false;

    @OneToOne
    @JoinColumn(name = "userfkid")
    private SystemUser user;
}
