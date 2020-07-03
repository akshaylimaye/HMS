package com.adlier.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adlier.dao.DoctorDao;
import com.adlier.model.Doctor;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	DoctorDao dao;

	@Override
	public String createDoctor(Doctor doctor) throws Exception {
		return dao.saveDoctor(doctor);
	}

	@Override
	public String getUsers(String userId) throws Exception {
		return dao.getUsers(userId);
	}

}
