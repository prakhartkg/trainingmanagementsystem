package com.yash.training.tms.domain;

import java.util.List;

public class Course {
	private String statusButton;
	private int course_id;
	private String courseTitle;
	private String description;
	private String referance_id;
	private boolean active=true;
	private String filePath;
	private int user_id;
	private String tooltip;
	private List<Heading> headings;
	

	
	public String getTooltip() {
		if(isActive()){
			tooltip="Inactive It";
		}else{
			tooltip="Active It";
		}
		
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
	
	public List<Heading> getHeadings() {
		return headings;
	}
	public void setHeadings(List<Heading> headings) {
		this.headings = headings;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReferance_id() {
		return referance_id;
	}
	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStatusButton() {
		if(isActive()){
			statusButton="Inactive_icon.jpg";
		}else{
			statusButton="Active_icon.jpg";
		}
		
		return statusButton;
	}
	public void setStatusButton(String statusButton) {
		this.statusButton = statusButton;
	}
	
	
}
