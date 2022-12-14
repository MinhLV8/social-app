package com.minhlv.socialappapi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_post")
@Document(indexName = "system_post")
public class PostEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5636313057165892526L;

    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
    private Long id;

    @Column(name = "caption", columnDefinition = "TEXT")
    private String caption;

    @NotNull
    @Column(name = "privacy")
    private short privacy;

    @NotNull
    @Column(name = "likes")
    private long likes = 0L;

    @Column(name = "shares")
    private long shares = 0L;

    @Column(name = "liked")
    private short liked = 0;

    @Column(name = "times")
    private long times = new Date().getTime();

    @NotNull
    @Column(name = "is_delete")
    private short isDelete = 0;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private Set<ImageEntity> images = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SystemCommentsEntity> comments = new HashSet<>();

    public PostEntity(String caption, @NotNull short privacy, AccountEntity account, Set<ImageEntity> images) {
        this.caption = caption;
        this.privacy = privacy;
        this.account = account;
        this.images = images;
    }
}
