package com.yash.training.tms.service;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.yash.training.tms.domain.Course;
import com.yash.training.tms.domain.Heading;
import com.yash.training.tms.domain.SubHeading;
import com.yash.training.tms.domain.User;
import com.yash.training.tms.util.util;


@Stateless
@LocalBean
public class CourseServiceBean implements CourseServiceBeanLocal {

	@Override
	public void saveCourse(Course course) {
		String sql="Insert into course(courseTitle,description,referance_id,active,user_id)Values('"+course.getCourseTitle()+
				"','"+course.getDescription()+
				"','"+course.getReferance_id()+
				"',"+course.isActive()+
				","+course.getUser_id()+
				")";
		System.out.println(sql);
		util.Update(sql);
	}

	@Override
	public List<Course> getAllCourses(User user) {
		List<Course> courses=new ArrayList<>();
		Course course;
		String sql="Select * from course where user_id="+user.getUser_id();
		ResultSet resultSet=util.select(sql);
		try {
			while(resultSet.next()){
				course=new Course();
				course.setCourse_id(resultSet.getInt(1));
				course.setCourseTitle(resultSet.getString(2));
				course.setDescription(resultSet.getString(3));
				course.setReferance_id(resultSet.getString(4));
				course.setActive(resultSet.getBoolean(6));
				courses.add(course);
			}
			resultSet.close();
		} catch (SQLException e) {
			util.disconnect();
			e.printStackTrace();
		}finally{
			util.disconnect();
		}
		
		return courses;
	}

	@Override
	public void saveHeading(Heading heading) {
		String sql="insert into heading(heading_text,course_id)values('"+heading.getHeading_text()+"',"+heading.getCourse_id()+")";
		util.Update(sql);
	}

	@Override
	public List<Heading> getAllHeading(int course_id) {
		String sql="select * from heading where course_id="+course_id;
		Heading heading;
		List<Heading> headings=new ArrayList<>();
		ResultSet resultSet=util.select(sql);
		try {
			while(resultSet.next()){
				heading=new Heading();
				heading.setHeading_id(resultSet.getInt(1));
				heading.setHeading_text(resultSet.getString(2));
				heading.setCourse_id(resultSet.getInt(3));
				headings.add(heading);
			}
			resultSet.close();
		} catch (SQLException e) {
			util.disconnect();
		}finally{
			util.disconnect();
		}
		return headings;
	}

	@Override
	public void svaeSubHeading(SubHeading subheading) {
		String sql="insert into subheading(subheading_text,heading_id,status)values('"+subheading.getSubheading_text()+"',"+subheading.getHeading_id()+",'"+subheading.getStatus()+"')";
		System.out.println(sql);
		util.Update(sql);
	}
	
	
	
	
	
	
	@Override
	public Course getDetailedCourse(int selectedCourseId) {
		List<Heading> headings;
		List<SubHeading> subHeadings;
		
		Course course=new Course();
		Heading heading;
		SubHeading subHeading;
		String sql="Select * from course where course_id="+selectedCourseId;
		System.out.println(sql);
		ResultSet resultSet=util.select(sql);
		try {
			while(resultSet.next()){
				headings=new ArrayList<>();
				course.setCourse_id(resultSet.getInt(1));
				course.setCourseTitle(resultSet.getString(2));
				course.setDescription(resultSet.getString(3));
				course.setReferance_id(resultSet.getString(4));
				course.setActive(resultSet.getBoolean(6));
					sql="select * from heading where course_id="+course.getCourse_id();
					System.out.println(sql);
					ResultSet resultSet3=util.select(sql);
						while(resultSet3.next()){
							subHeadings=new ArrayList<>();
							heading=new Heading();
							heading.setHeading_id(resultSet3.getInt(1));
							heading.setHeading_text(resultSet3.getString(2));
							heading.setCourse_id(selectedCourseId);
							headings.add(heading);
								sql="select * from subheading where heading_id="+heading.getHeading_id();
								System.out.println(sql);
								ResultSet resultSet2=util.select(sql);
									while (resultSet2.next()) {
										subHeading=new SubHeading();
										subHeading.setSubheading_id(resultSet2.getInt(1));
										subHeading.setSubheading_text(resultSet2.getString(2));
										subHeading.setHeading_id(heading.getHeading_id());
										subHeading.setStatus(resultSet2.getString(4));
										subHeadings.add(subHeading);
							
									}
									resultSet2.close();
						heading.setSubHeadings(subHeadings);
						}
						resultSet3.close();
				course.setHeadings(headings);
			resultSet.close();
			}
		} catch (SQLException e) {
			util.disconnect();
		}finally{
			util.disconnect();
		}
		
		
		return course;
		
	}

	@Override
	public void updateCourseStatus(Course course) {
		String sql;
		if(course.isActive()){
			sql="Update course set active=false where course_id="+course.getCourse_id();
		}else{
			sql="Update course set active=true where course_id="+course.getCourse_id();
		}
		util.Update(sql);
	}

	@Override
	public void deleteCourse(int selectedCourseId) {
		String sql="delete from course where course_id="+selectedCourseId;
		util.Update(sql);
		
	}

	@Override
	public int getActiveCourses(User user) {
		String sql="select count(*) from course where user_id="+user.getUser_id()+" and active="+true;
		int totalActiveCourse=0;
		ResultSet resultSet=util.select(sql);
		try {
			if(resultSet.next()){
			totalActiveCourse=resultSet.getInt(1);	
			}
		} catch (SQLException e) {
			util.disconnect();
			e.printStackTrace();
		}finally{
			util.disconnect();
		}
		return totalActiveCourse;
	}

	@Override
	public void deleteSubHeading(SubHeading subheading) {
		String sql="Delete from subheading where subheading_id="+subheading.getSubheading_id();
		util.Update(sql);
				
	}

	@Override
	public void deleteHeading(int heading_id) {
		String sql="Delete from heading where heading_id="+heading_id;
		System.out.println(sql);
		util.Update(sql);
	}

	
	
	
	@Override
	public List<Course> getActiveCourseList() {
		Course course;		
		List<Course> courses=new ArrayList<>();
		String sql="select * from course where active="+true;
		ResultSet resultSet=util.select(sql);
		try{
		while(resultSet.next()){
			course=getDetailedCourse(resultSet.getInt(1));
			courses.add(course);
		}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courses;
	}

	@Override
	public void updateSubHeadingStatus(int subHeading_id, String subheadingStatus) {
		String sql="update subheading set status='"+subheadingStatus+"' where subheading_id="+subHeading_id;
		util.Update(sql);
		
	}

	
}
