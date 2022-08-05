package com.minhlv.socialappapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "thongke_truycap")
public class ThongKeTruyCapEntity {

    @Id
    @GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.util.IDGenerator")
    private long id;

    @Column(name = "nam")
    private int nam;

    @Column(name = "thang")
    private int thang;

    @Column(name = "ngay")
    private int ngay;

    @Column(name = "ngaytao")
    private Date ngaytao = new Date();

    @Column(name = "nguoitao")
    private String nguoiTao = "";

    @Column(name = "url")
    private String url;

    public ThongKeTruyCapEntity(int nam, int thang, int ngay, String url) {
        super();
        this.nam = nam;
        this.thang = thang;
        this.ngay = ngay;
        this.url = url;
    }

}
