package com.adlier.dao;

import com.adlier.model.Doctor;

public interface DoctorDao {

	String saveDoctor(Doctor doctor) throws Exception;

	String getUsers(String userId) throws Exception;

}
