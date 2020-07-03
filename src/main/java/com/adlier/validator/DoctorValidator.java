package com.adlier.validator;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.adlier.dao.DoctorDaoImpl;
import com.adlier.model.Doctor;
import com.adlier.model.Specialization;
import com.adlier.util.*;

public class DoctorValidator {

	private Doctor doctor;
	private JSONObject request;
	private JSONArray errorArray = new JSONArray();
	HospitalManagementUtil utility = new HospitalManagementUtil();

	public Doctor getDoctor() {
		return doctor;
	}

	private DoctorValidator(JSONObject request) {
		this.request = request;
	}

	public static synchronized DoctorValidator getValidator(JSONObject request) {
		return new DoctorValidator(request);
	}

	public JSONArray validateDoctorObject() throws Exception {
		doctor = new Doctor();
		if (utility.mandatoryField(request, Constants.FIRST_NAME)) {
			doctor.setFirstName(request.get(Constants.FIRST_NAME).toString());
		} else {
			errorMessages(Constants.FIRST_NAME + Constants.MANDATORY);
		}
		if (utility.mandatoryField(request, Constants.LAST_NAME)) {
			doctor.setLastName(request.get(Constants.LAST_NAME).toString());
		} else {
			errorMessages(Constants.LAST_NAME + Constants.MANDATORY);
		}
		if (utility.mandatoryField(request, Constants.MIDDLE_NAME)) {
			doctor.setMiddleName(request.get(Constants.MIDDLE_NAME).toString());
		}
		if (utility.mandatoryField(request, Constants.AGE)
				&& utility.numericByString(request.get(Constants.AGE).toString())) {
			doctor.setAge(Integer.valueOf(request.get(Constants.AGE).toString()));
		} else {
			errorMessages(Constants.AGE + Constants.MANDATORY);
		}

		if (utility.mandatoryField(request, Constants.ALTERNATE_NUMBER)) {
			if (utility.numericByString(request.get(Constants.ALTERNATE_NUMBER).toString())
					&& utility.isValidPhoneNumber(request.get(Constants.ALTERNATE_NUMBER).toString())) {
				doctor.setAlternateNumber(request.get(Constants.ALTERNATE_NUMBER).toString());
			} else {
				errorMessages(Constants.ALTERNATE_NUMBER);
			}

		}
		if (utility.mandatoryField(request, Constants.SPECIALIZED)) {
			Specialization spec = new Specialization();
			spec.setSpecializationId(Integer.valueOf(request.get(Constants.SPECIALIZED).toString()));
			doctor.setSpecialized(spec);
		} else {
			errorMessages(Constants.SPECIALIZED + Constants.MANDATORY);
		}
		if (utility.mandatoryField(request, Constants.EXPERIENCE)
				&& utility.numericByString(request.get(Constants.EXPERIENCE).toString())) {
			doctor.setExperience(Integer.valueOf(request.get(Constants.EXPERIENCE).toString()));
		} else {
			errorMessages(Constants.EXPERIENCE + Constants.MANDATORY);
		}
		if (utility.mandatoryField(request, Constants.DEGREE)) {
			doctor.setDegree(request.get(Constants.DEGREE).toString());
		} else {
			errorMessages(Constants.DEGREE + Constants.MANDATORY);
		}
		if (utility.mandatoryField(request, Constants.CONTRACT_START_DATE)
				&& utility.mandatoryField(request, Constants.CONTRACT_END_DATE)) {
			String sDate = request.get(Constants.CONTRACT_START_DATE).toString();
			String eDate = request.get(Constants.CONTRACT_END_DATE).toString();
			if (utility.validateDate(sDate, eDate)) {
				Date startDate = utility.convertStringToDate(sDate);
				Date endDate = utility.convertStringToDate(eDate);
				doctor.setContractStartDate(startDate);
				doctor.setContractEndDate(endDate);
			} else {
				errorMessages(Constants.START_DATE_END_DATE);
			}
		} else {
			errorMessages(Constants.CONTRACT_START_DATE + " and " + Constants.CONTRACT_END_DATE + Constants.MANDATORY);
		}
		if (utility.mandatoryField(request, Constants.CREATED_BY)) {
			doctor.setCreatedBy(request.get(Constants.CREATED_BY).toString());
		} else {
			errorMessages(Constants.CREATED_BY);
		}

		if (utility.mandatoryField(request, Constants.PASS_WORD)) {
			if (utility.isValidPassword(request.get(Constants.PASS_WORD).toString())) {
				EncryptionDecryptionHandler handler = new EncryptionDecryptionHandler();
				doctor.setPassword(handler.encrypt(request.get(Constants.PASS_WORD).toString()));
			} else {
				errorMessages(Constants.PASS_WORD_POLICY);
			}
		} else {
			errorMessages(Constants.PASS_WORD);
		}
		if (errorArray.isEmpty()) {
			if (utility.mandatoryField(request, Constants.USERNAME)) {
				if (SpringContext.getBean(DoctorDaoImpl.class).isUnique(Constants.USERNAME,
						request.get(Constants.USERNAME).toString())) {
					doctor.setUsername(request.get(Constants.USERNAME).toString().toLowerCase());
				} else {
					errorMessages(Constants.USERNAME_ALREADY_EXISTS);
				}
			} else {
				errorMessages(Constants.USERNAME);
			}
			if (utility.mandatoryField(request, Constants.PRIMARY_NUMBER)
					&& utility.numericByString(request.get(Constants.PRIMARY_NUMBER).toString())
					&& utility.isValidPhoneNumber(request.get(Constants.PRIMARY_NUMBER).toString())) {
				if (SpringContext.getBean(DoctorDaoImpl.class).isUnique(Constants.PRIMARY_NUMBER,
						request.get(Constants.PRIMARY_NUMBER).toString())) {
					doctor.setPhoneNumber(request.get(Constants.PRIMARY_NUMBER).toString());
				} else {
					errorMessages(Constants.PHONE_ALREADY_EXISTS);
				}

			} else {
				errorMessages(Constants.PRIMARY_NUMBER);
			}
			if (request.has(Constants.EMAIL)) {
				if (utility.isValidEmail(request.get(Constants.EMAIL).toString())) {
					if (SpringContext.getBean(DoctorDaoImpl.class).isUnique(Constants.EMAIL,
							request.get(Constants.EMAIL).toString())) {
						doctor.setEmailId(request.get(Constants.EMAIL).toString());
					} else {
						errorMessages(Constants.EMAIL_ALREADY_EXISTS);
					}

				} else {
					errorMessages(Constants.EMAIL);
				}
			}
		}

		return errorArray;
	}

	private void errorMessages(String key) {
		JSONObject json = new JSONObject();
		json.put("key", Constants.INVALID_DATA);
		json.put("message", key);
		errorArray.put(json);
	}
}
