package com.adlier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONObject;

import com.adlier.util.Constants;
import com.adlier.util.HospitalManagementUtil;
import com.google.gson.Gson;

@Entity
@Table(name = "doctor", indexes = {
		@Index(name = "doctor_index", columnList = "doctor_id, username, email, phonenumber") })
public class Doctor extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctor_id")
	private Integer doctorId;

	@ManyToOne
	@JoinColumn(name = "specialized", nullable = false)
	private Specialization specialized;

	@Column(name = "experience", nullable = false)
	private Integer experience;

	@Column(name = "degree", nullable = false)
	private String degree;

	@Column(name = "contractStartDate", nullable = false)
	private Date contractStartDate;

	@Column(name = "contractEndDate", nullable = false)
	private Date contractEndDate;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email", nullable = false)
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Specialization getSpecialized() {
		return specialized;
	}

	public void setSpecialized(Specialization specialized) {
		this.specialized = specialized;
	}

	public JSONObject serialize() {
		Gson gson = new Gson();
		JSONObject json = new JSONObject(gson.toJson(this));
		json.put(Constants.CONTRACT_START_DATE, HospitalManagementUtil.convertDateToString(this.contractStartDate));
		json.put(Constants.CONTRACT_END_DATE, HospitalManagementUtil.convertDateToString(this.contractEndDate));
		json.remove(Constants.STATUS);
		json.remove(Constants.PASS_WORD);
		return json;
	}

}
