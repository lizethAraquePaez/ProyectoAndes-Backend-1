package com.co.andes.management.domain.repository.model.database;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class CountryEntity implements Serializable {

	final static Logger logger = Logger.getLogger(CountryEntity.class);
	private static final long serialVersionUID = -2463354084291480284L;

	@Id
	@Column(name = "id")
	private Integer id;


	@Column( name = "country", length = 200)
	@NotNull
	private String  country;

	@OneToMany(mappedBy="country")
	private List<LocateEntity> locateEntity;



}
