package com.adailsilva.jdbc.entities;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Model {

	@EqualsAndHashCode.Include
	private Long id;

	private String name;

	private Date date;

	public Model() {
		super();
	}

	public Model(Long id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}

}
