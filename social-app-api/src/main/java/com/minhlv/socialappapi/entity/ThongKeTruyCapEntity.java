package com.minhlv.socialappapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "thongke_truycap")
public class ThongKeTruyCapEntity {

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.minhlv.socialappapi.utils.IDGenerator")
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
