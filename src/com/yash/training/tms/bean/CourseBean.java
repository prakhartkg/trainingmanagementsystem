package com.yash.training.tms.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.yash.training.tms.domain.Course;
import com.yash.training.tms.domain.Heading;
import com.yash.training.tms.domain.SubHeading;
import com.yash.training.tms.domain.User;
import com.yash.training.tms.service.CourseServiceBeanLocal;

@ManagedBean
@SessionScoped
public class CourseBean {
	private int totalCourses;
	private int totalActiveCourses;
	private int selectedCourseId;
	private User user;
	private int course_id;
	private int heading_id;
	private String heading_text;
	private String subheading_text;
	private String courseTitle;
	private String description;
	private String referance_id;
	private boolean active=true;
	private String filePath;
	private List<Course> courses=new ArrayList<>();
	private List<Heading> headings=new ArrayList<>();
	private Course course=new Course();
	private Heading heading=new Heading();
	private SubHeading subheading=new SubHeading();
	
	@EJB CourseServiceBeanLocal courseService;
	
	public CourseBean() {
		user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		}

	public SubHeading getSubheading() {
		return subheading;
	}


	public void setSubheading(SubHeading subheading) {
		this.subheading = subheading;
	}


	public int getTotalCourses() {
		totalCourses=courseService.getAllCourses(user).size();
		return totalCourses;
	}


	public void setTotalCourses(int totalCourses) {
		this.totalCourses = totalCourses;
	}


	public int getTotalActiveCourses() {
		totalActiveCourses=courseService.getActiveCourses(user);
		return totalActiveCourses;
	}


	public void setTotalActiveCourses(int totalActiveCourses) {
		this.totalActiveCourses = totalActiveCourses;
	}

	
		
	public Course getCourse() {
		
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getSelectedCourseId() {
		return selectedCourseId;
	}
	public void setSelectedCourseId(int selectedCourseId) {
		this.selectedCourseId = selectedCourseId;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	
	
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
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
	public List<Course> getCourses() {
		courses=courseService.getAllCourses(user);
		return courses;
	}
		
	public String getSubheading_text() {
		return subheading_text;
	}
	public void setSubheading_text(String subheading_text) {
		this.subheading_text = subheading_text;
	}
	public List<Heading> getHadings() {
		return headings;
	}
	
	
	/*
	 * Method to Save Course 
	 *  
	 * */
	
	
	public String save(){
		course.setCourseTitle(courseTitle);
		course.setDescription(description);
		course.setReferance_id(referance_id);
		course.setActive(active);
		course.setUser_id(user.getUser_id());
		courseService.saveCourse(course);
		course_id=-1;
		description=null;
		referance_id=null;
		return "createcourse.xhtml?msg=Saved Successfully & faces-redirect=true";
	}
	
	public String saveHeading(int btn){
		heading.setCourse_id(course_id);
		heading.setHeading_text(heading_text);
		course_id=-1;
		heading_id=-1;
		heading_text="";
		courseService.saveHeading(heading);
		if(btn==1){
			return "createcourse.xhtml";
		}
		return null;
	}
	
	public String saveSubHeading(int btn){		
		subheading.setHeading_id(heading_id);
		subheading.setSubheading_text(subheading_text);
		courseService.svaeSubHeading(subheading);
		heading_id=-1;
		subheading_text="";
		selectedCourseId=-1;
		if(btn==1){
			return "createcourse.xhtml";
		}
		return null;
	}
	
	public String viewAllCources(){
		courses=courseService.getAllCourses(user);
		return "viewcourses.xhtml?faces-redirect=true";
	}
	
	
	
	public void selectHeadings(ValueChangeEvent e){
		headings=courseService.getAllHeading(Integer.parseInt( e.getNewValue().toString()));
		
	}
	
	public String viewDetails(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedCourseId", selectedCourseId);
		course=courseService.getDetailedCourse(selectedCourseId);
		return "viewcoursedetail.xhtml";
	}
	
	public String deleteCourse(){
		
		System.out.println(selectedCourseId);
		courseService.deleteCourse(selectedCourseId);
		courses=courseService.getAllCourses(user);
		return null;
	}
	
	public String updateCourseStatus(){
		System.out.println(course.getCourseTitle());
		courseService.updateCourseStatus(course);
		courses=courseService.getAllCourses(user);
		return null;
	}
	
	
	public String deleteSubHeading(){
		System.out.println(subheading.getSubheading_id());
		courseService.deleteSubHeading(subheading);
		selectedCourseId=(int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedCourseId");
		course=courseService.getDetailedCourse(selectedCourseId);
		return "viewcoursesdetail.xhtml?faces-redirect=true";
	}
	
	public String deleteHeading(){
		System.out.println(heading_id);
		courseService.deleteHeading(heading_id);
		selectedCourseId=(int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedCourseId");
		course=courseService.getDetailedCourse(selectedCourseId);
		return "viewcoursesdetail.xhtml?faces-redirect=true";
	}

}
