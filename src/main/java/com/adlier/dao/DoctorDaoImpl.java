package com.adlier.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adlier.model.Doctor;
import com.adlier.model.Status;
import com.adlier.util.Constants;

@Repository
public class DoctorDaoImpl implements DoctorDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	static Logger LOGGER = Logger.getLogger(DoctorDaoImpl.class);

	@Override
	public String saveDoctor(Doctor doctor) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Entering in saveDoctors of DoctorController- " + doctor.toString());
		}
		Session session = null;
		Transaction tx = null;
		JSONObject json = new JSONObject();
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Status status = (Status) session.load(Status.class, Constants.ACTIVE);
			doctor.setStatus(status);
			doctor.setCreatedDate(new Date());
			session.save(doctor);
			session.flush();
			tx.commit();
			json.put("doctorId", doctor.getDoctorId());
			json.put(Constants.STATUS, Constants.SUCCESS);
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();
			}
			LOGGER.error("Exception- " + e);
		} catch (Exception ex) {
			LOGGER.error("Exception- " + ex);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return json.toString();
	}

	@Transactional
	public boolean isUnique(String type, String credential) {
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Doctor.class, "doctor");
			criteria.add(Restrictions.eq("doctor.status.statusId", 1));
			if(type.equals(Constants.USERNAME)) {
				criteria.add(Restrictions.eq("doctor.username", credential.toLowerCase()));
			}else if(type.equals(Constants.PRIMARY_NUMBER)) {
				criteria.add(Restrictions.eq("doctor.phoneNumber", credential));
			}else {
				criteria.add(Restrictions.eq("doctor.emailId", credential.toLowerCase()));
			}
			if (criteria.list().isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public String getUsers(String doctorId) throws Exception {
		Session session = null;
		JSONArray doctors = new JSONArray();
		try {
			session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Doctor.class, "doctor");
			criteria.add(Restrictions.eq("doctor.status.statusId", 1));
			if (null != doctorId) {
				criteria.add(Restrictions.eq("doctor.doctorId", Integer.valueOf(doctorId)));
			}
			List<Doctor> criList = criteria.list();
			for(Doctor doc : criList) {
				JSONObject json = doc.serialize();
				doctors.put(json);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception- " + ex);
		}
		return doctors.toString();
	}

}
