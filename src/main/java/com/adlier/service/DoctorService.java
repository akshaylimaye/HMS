package com.adlier.service;

import com.adlier.model.Doctor;

public interface DoctorService {

	String createDoctor(Doctor doctor) throws Exception;

	String getUsers(String userId) throws Exception;

}
