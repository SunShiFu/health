package com.shiyanlou.lesson10.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class Sport {

	private int id;
	private String name;
	private String description;
	private int consumeEnergy;
	
	public Sport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sport(String name, String description, int consumeEnergy) {
		super();
		this.name = name;
		this.description = description;
		this.consumeEnergy = consumeEnergy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getConsumeEnergy() {
		return consumeEnergy;
	}

	public void setConsumeEnergy(int consumeEnergy) {
		this.consumeEnergy = consumeEnergy;
	}

	@Override
	public String toString() {
		return "Sport [id=" + id + ", name=" + name + ", description=" + description + ", consumeEnergy="
				+ consumeEnergy + "]";
	}	
}
