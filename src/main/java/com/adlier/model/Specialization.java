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
@Table(name = "specialization")
public class Specialization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "specialization_id")
	private Integer specializationId;
	@Column(name = "specialization_name", nullable = false)
	private String specializationName;
	@Column(name = "specialization_description")
	private String specializationDescription;

	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public String getSpecializationDescription() {
		return specializationDescription;
	}

	public void setSpecializationDescription(String specializationDescription) {
		this.specializationDescription = specializationDescription;
	}

	public JSONObject serialize() {
		Gson gson = new Gson();
		JSONObject json = new JSONObject(gson.toJson(this));
		return json;
	}

}
