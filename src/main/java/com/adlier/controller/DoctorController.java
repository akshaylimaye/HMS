package com.adlier.controller;

import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adlier.model.Doctor;
import com.adlier.service.DoctorService;
import com.adlier.util.Constants;
import com.adlier.util.HospitalManagementUtil;
import com.adlier.validator.DoctorValidator;

import org.json.JSONArray;
import org.json.JSONObject;

@Controller
public class DoctorController {

	@Autowired
	DoctorService service;

	static Logger LOGGER = Logger.getLogger(DoctorController.class);

	private DoctorValidator validator;

	@RequestMapping(method = RequestMethod.POST, value = "/doctor")
	@ResponseBody
	public ResponseEntity<String> saveDoctors(@RequestBody String request) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Entering in saveDoctors of DoctorController- " + request);
		}
		JSONObject json = new JSONObject(request);
		JSONArray validationArray = new JSONArray();
		try {

			validator = DoctorValidator.getValidator(json);
			validationArray = validator.validateDoctorObject();
			Doctor doctor = new Doctor();
			if (!validationArray.isEmpty()) {
				throw new Exception(validationArray.toString());
			}
			doctor = validator.getDoctor();
			String jsn = service.createDoctor(doctor);
			return ResponseEntity.status(HttpStatus.CREATED).body(jsn);
		} catch (Exception e) {
			LOGGER.error("Exception- ", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationArray.toString());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/doctor")
	@ResponseBody
	public ResponseEntity<String> getUsers(@QueryParam("doctorId") String doctorId) {
		HospitalManagementUtil utility = new HospitalManagementUtil();
		try {
			if (null != doctorId) {
				if (!utility.numericByString(doctorId)) {
					JSONObject error = new JSONObject();
					error.put("key", Constants.INVALID_DATA);
					error.put("message", doctorId);
					throw new Exception(error.toString());
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(service.getUsers(doctorId));
		} catch (Exception e) {
			LOGGER.error("Exception- ", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(doctorId);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/doctor/{doctorId}")
	public ResponseEntity<?> deleteDoctor(@PathParam("doctorId") String doctorId){
		
		return null;
	}
}
