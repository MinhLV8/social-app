package com.minhlv.socialappapi.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_comments")
@Document(indexName = "system_comments")
public class SystemCommentsEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5720130349551352752L;

    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "times")
    private long times;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

}
