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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "system_permission", schema = "public")
public class SystemPermission {

    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "vn.unitech.dyndata.util.IDGenerator")
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

    @Column(name = "htmlId")
    private String htmlId;

    @Column(name = "menuTop")
    private boolean menuTop;

    @Column(name = "invisible")
    private boolean invisible = true;

    @Column(name = "id_chuc_nang", length = 255)
    private String idChucNang;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_grouppermission", schema = "public", joinColumns = {
            @JoinColumn(name = "permissionfkid", nullable = false, updatable = false)}, inverseJoinColumns = {
                    @JoinColumn(name = "groupfkid", nullable = false, updatable = false)})
    private Set<SystemGroup> systemGroups = new HashSet<>(0);

    public SystemPermission() {
    }

    public SystemPermission(Long id) {
        this.id = id;
    }

    public SystemPermission(Long id, String name, String permission, String note, Short isEnable, Short sort, Long idParent, String url, Integer type,
            String icon, String htmlId, boolean menuTop, Set<SystemGroup> systemGroups) {
        super();
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.note = note;
        this.isEnable = isEnable;
        this.sort = sort;
        this.idParent = idParent;
        this.url = url;
        this.type = type;
        this.icon = icon;
        this.htmlId = htmlId;
        this.menuTop = menuTop;
        this.systemGroups = systemGroups;
    }

    public SystemPermission(Long id, String name, String permission, String note, Short isEnable, Short sort, Long idParent, String url, Integer type,
            String icon, String htmlId, Set<SystemGroup> systemGroups) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.note = note;
        this.isEnable = isEnable;
        this.sort = sort;
        this.idParent = idParent;
        this.url = url;
        this.type = type;
        this.icon = icon;
        this.htmlId = htmlId;
        this.systemGroups = systemGroups;
    }

    public SystemPermission(Long id, String name, String permission, String note, Short isEnable, Short sort, Long idParent, String url,
            Set<SystemGroup> systemGroups) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.note = note;
        this.isEnable = isEnable;
        this.sort = sort;
        this.idParent = idParent;
        this.url = url;
        this.systemGroups = systemGroups;
    }

    public SystemPermission(Long id, String name, String permission, String note, Short isEnable, Short sort, Long idParent, String url, Integer type,
            String icon, String htmlId, boolean menuTop, String idChucNang, Set<SystemGroup> systemGroups) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.note = note;
        this.isEnable = isEnable;
        this.sort = sort;
        this.idParent = idParent;
        this.url = url;
        this.type = type;
        this.icon = icon;
        this.htmlId = htmlId;
        this.menuTop = menuTop;
        this.idChucNang = idChucNang;
        this.systemGroups = systemGroups;
    }
}
