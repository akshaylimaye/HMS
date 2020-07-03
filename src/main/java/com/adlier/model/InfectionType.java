package com.adlier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

import com.google.gson.Gson;

@Entity
@Table(name = "infection")
public class InfectionType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "infection_id")
	private Integer infectionId;
	@Column(name = "infection_name", nullable = false)
	private String infectionName;
	@Column(name = "infection_description")
	private String infectionDescription;

	public Integer getInfectionId() {
		return infectionId;
	}

	public void setInfectionId(Integer infectionId) {
		this.infectionId = infectionId;
	}

	public String getInfectionName() {
		return infectionName;
	}

	public void setInfectionName(String infectionName) {
		this.infectionName = infectionName;
	}

	public String getInfectionDescription() {
		return infectionDescription;
	}

	public void setInfectionDescription(String infectionDescription) {
		this.infectionDescription = infectionDescription;
	}

	public JSONObject serialize() {
		Gson gson = new Gson();
		JSONObject json = new JSONObject(gson.toJson(this));
		return json;
	}

}
