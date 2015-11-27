package com.yash.training.tms.domain;

import java.io.Serializable;

public class SubHeading implements Serializable {

	private static final long serialVersionUID = 1L;
	private int subheading_id;
	private String subheading_text;
	private int heading_id;
	private String status="Not started";
	
	public int getSubheading_id() {
		return subheading_id;
	}
	public void setSubheading_id(int subheading_id) {
		this.subheading_id = subheading_id;
	}
	public String getSubheading_text() {
		return subheading_text;
	}
	public void setSubheading_text(String subheading_text) {
		this.subheading_text = subheading_text;
	}
	public int getHeading_id() {
		return heading_id;
	}
	public void setHeading_id(int heading_id) {
		this.heading_id = heading_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
