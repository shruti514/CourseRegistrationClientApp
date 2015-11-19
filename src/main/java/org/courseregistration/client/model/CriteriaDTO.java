package org.courseregistration.client.model;

import javax.ws.rs.QueryParam;

public class CriteriaDTO {
	@QueryParam("coursename")
	String coursename;

	@QueryParam("lastname")
	String lastname;

	@QueryParam("price")
	int price;

	@QueryParam("gteprice")
	int gteprice;

	@QueryParam("lteprice")
	int lteprice;

	@QueryParam("dayofweek")
	String dayofweek;

	@QueryParam("semester")
	String semester;

	@QueryParam("coursecode")
	String coursecode;

	/**
	 * get course name
	 * @return String
	 */
	public String getCoursename() {
		return coursename;
	}

	/**
	 * set course name
	 * @param coursename
	 */
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	/**
	 * get last name
	 * @return String
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * set last name
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * get price
	 * @return int
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * set price
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * get Gteprice
	 * @return int
	 */
	public int getGteprice() {
		return gteprice;
	}

	/**
	 * set Gteprice
	 * @param gteprice
	 */
	public void setGteprice(int gteprice) {
		this.gteprice = gteprice;
	}

	/**
	 * get Lte price
	 * @return int
	 */
	public int getLteprice() {
		return lteprice;
	}

	/**
	 * set Lte price
	 * @param lteprice
	 */
	public void setLteprice(int lteprice) {
		this.lteprice = lteprice;
	}

	/**
	 * get day of week
	 * @return String
	 */
	public String getDayofweek() {
		return dayofweek;
	}

	/**
	 * sets day of week
	 * @param dayofweek
	 */
	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	/**
	 * get semester
	 * @return String
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * set semester
	 * @param semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * get course code
	 * @return String
	 */
	public String getCoursecode() {
		return coursecode;
	}

	/**
	 * set course code
	 * @param coursecode
	 */
	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

}
