package com.minhlv.socialappapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "system_role", schema = "public")
public class SystemRoleEntity implements Serializable {

	private static final long serialVersionUID = -46519118286926122L;
	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid",
			strategy = "com.minhlv.socialappapi.utils.IDGenerator")
	private Long id;

	@NotNull
	@Column(name = "role", length = 25)
	private String role;

}
