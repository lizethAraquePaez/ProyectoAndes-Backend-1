package com.co.andes.management.domain.repository.model.database;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	final static Logger logger = Logger.getLogger(UserEntity.class);
	private static final long serialVersionUID = -2463354084291480284L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;


	@Column( name = "email", length = 30)
	@NotNull
	private String  email;

	@Column( name = "pasword", length = 15)
	@NotNull
	private String  password;

	@Column( name = "isActive", columnDefinition = "int default 0")
	@NotNull
	private int  isActive;

	@OneToOne
	@JoinColumn(name = "id")
	@MapsId
	private RolesEntity rol;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RolesEntity getRol() {
		return rol;
	}

	public void setRol(RolesEntity rol) {
		this.rol = rol;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
}
