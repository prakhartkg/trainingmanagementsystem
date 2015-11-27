package com.yash.training.tms.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.yash.training.tms.domain.Course;
import com.yash.training.tms.domain.User;
import com.yash.training.tms.service.CourseServiceBeanLocal;

@SessionScoped
@ManagedBean
public class TrainerBean {

		private int course_id;
		private Course course;
		private List<Course> courses;
		private User user;
		private int subHeading_id=-1;
		private int subheadingStatus;
		@EJB CourseServiceBeanLocal courseService;
		public TrainerBean() {
			user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		}
		
		
		
		public int getSubHeading_id() {
			return subHeading_id;
		}



		public void setSubHeading_id(int subHeading_id) {
			this.subHeading_id = subHeading_id;
		}





		public int getSubheadingStatus() {
			return subheadingStatus;
		}



		public void setSubheadingStatus(int subheadingStatus) {
			this.subheadingStatus = subheadingStatus;
		}



		public int getCourse_id() {
			return course_id;
		}
		public void setCourse_id(int course_id) {
			this.course_id = course_id;
		}
		public Course getCourse() {
			return course;
		}
		public void setCourse(Course course) {
			this.course = course;
		}
		public List<Course> getCourses() {
			courses=courseService.getActiveCourseList();
			return courses;
		}
		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}
		
		
		public void selectCourse(ValueChangeEvent e){
			course_id=Integer.parseInt(e.getNewValue().toString());
			System.out.println("------------------"+course_id+"-----------------");
			course=courseService.getDetailedCourse(course_id);
		}
		
		public void selectstatus(ValueChangeEvent e){
			subheadingStatus=Integer.parseInt(e.getNewValue().toString());
			}
		
		public String updateStatus(){
			System.out.println("sub ==============----"+subHeading_id);
			System.out.println("-----------------------"+subheadingStatus);
			if(subheadingStatus==0)
				courseService.updateSubHeadingStatus(subHeading_id,"Not Started");
			if(subheadingStatus==1)
				courseService.updateSubHeadingStatus(subHeading_id,"Pending");
			if(subheadingStatus==2)	
				courseService.updateSubHeadingStatus(subHeading_id,"Completed");
			
			course=courseService.getDetailedCourse(course_id);
			return "TrainerDashBoard.xhtml?faces-redirect=true";
		}
}
