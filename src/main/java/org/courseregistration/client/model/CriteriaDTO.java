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

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getGteprice() {
		return gteprice;
	}

	public void setGteprice(int gteprice) {
		this.gteprice = gteprice;
	}

	public int getLteprice() {
		return lteprice;
	}

	public void setLteprice(int lteprice) {
		this.lteprice = lteprice;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

}
