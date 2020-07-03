package com.adlier.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONObject;

public class HospitalManagementUtil {

	public boolean validateDate(String sDate, String eDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setLenient(false);
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = formatter.parse(sDate);
			endDate = formatter.parse(eDate);
			if (startDate.after(endDate))
				return false;
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean mandatoryField(JSONObject request, String key) {
		if (request.has(key) && request.get(key).toString() != null && !request.get(key).toString().isEmpty())
			return true;
		return false;
	}

	public boolean numericByString(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]+");
		if (strNum == null || strNum.isEmpty()) {
			return false;
		}
		return pattern.matcher(strNum).matches();

	}

	public Date convertStringToDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setLenient(false);
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {

		}
		return date;
	}

	public static String convertDateToString(Date sdate) {
		try {
			DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
			String strDate = format.format(sdate);
			return strDate;
		} catch (Exception e) {

		}
		return null;

	}

	public boolean isValidPassword(String password) {
		Pattern pattern = Pattern.compile(Constants.PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public boolean isValidEmail(String email) {
		if (null == email || email.isEmpty())
			return false;
		return EmailValidator.getInstance().isValid(email);
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		if (null == phoneNumber || phoneNumber.isEmpty())
			return false;
		Pattern pattern = Pattern.compile(Constants.PHONENUMBER_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
}
