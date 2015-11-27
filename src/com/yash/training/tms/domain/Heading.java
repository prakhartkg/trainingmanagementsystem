package com.yash.training.tms.domain;

import java.util.List;

public class Heading {
	private int heading_id;
	private String heading_text;
	private int course_id;
	private List<SubHeading> subHeadings;
	
	
	public List<SubHeading> getSubHeadings() {
		return subHeadings;
	}
	public void setSubHeadings(List<SubHeading> subHeadings) {
		this.subHeadings = subHeadings;
	}
	public int getHeading_id() {
		return heading_id;
	}
	public void setHeading_id(int heading_id) {
		this.heading_id = heading_id;
	}
	public String getHeading_text() {
		return heading_text;
	}
	public void setHeading_text(String heading_text) {
		this.heading_text = heading_text;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	
}
