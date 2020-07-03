package com.adlier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patients extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_id")
	private Integer patientId;
	@Column(name = "blood_pressure")
	private String bloodPressure;
	@Column(name = "weight")
	private String weight;
	@Column(name = "height")
	private String height;
	@Column(name = "bmi")
	private String BMI;
	@Column(name = "severity_level", nullable = false)
	private String severityLevel;
	@ManyToOne
	@JoinColumn(name = "infection_type", nullable = false)
	private InfectionType infectionType;
	@Column(name = "infection_description")
	private String infectionDescription;
	@Column(name = "consultation_date", nullable = false)
	private Date consultationDate;
	@Column(name = "admit", nullable = false)
	private Boolean admit;
	@Column(name = "room_no")
	private String roomNo;
	@Column(name = "admit_date")
	private Date admitDate;
	@Column(name = "discharge_date")
	private Date dischargeDate;
	@Column(name = "patient_username")
	private String patientUsername;
	@Column(name = "patient_password")
	private String patientPassword;
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBMI() {
		return BMI;
	}

	public void setBMI(String bMI) {
		BMI = bMI;
	}

	public String getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}

	public InfectionType getInfectionType() {
		return infectionType;
	}

	public void setInfectionType(InfectionType infectionType) {
		this.infectionType = infectionType;
	}

	public String getInfectionDescription() {
		return infectionDescription;
	}

	public void setInfectionDescription(String infectionDescription) {
		this.infectionDescription = infectionDescription;
	}

	public Date getConsultationDate() {
		return consultationDate;
	}

	public void setConsultationDate(Date consultationDate) {
		this.consultationDate = consultationDate;
	}

	public Boolean getAdmit() {
		return admit;
	}

	public void setAdmit(Boolean admit) {
		this.admit = admit;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Date getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getPatientUsername() {
		return patientUsername;
	}

	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	public String getPatientPassword() {
		return patientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
