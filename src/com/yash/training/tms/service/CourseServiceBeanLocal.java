package com.yash.training.tms.service;

import java.util.List;

import javax.ejb.Local;

import com.yash.training.tms.domain.Course;
import com.yash.training.tms.domain.Heading;
import com.yash.training.tms.domain.SubHeading;
import com.yash.training.tms.domain.User;

@Local
public interface CourseServiceBeanLocal {

	void saveCourse(Course course);

	void saveHeading(Heading heading);

	List<Heading> getAllHeading(int course_id);

	void svaeSubHeading(SubHeading subheading);

	List<Course> getAllCourses(User user);

	Course getDetailedCourse(int selectedCourseId);

	void updateCourseStatus(Course course);

	void deleteCourse(int selectedCourseId);
	
	int getActiveCourses(User user);

	void deleteSubHeading(SubHeading subheading);

	void deleteHeading(int heading_id);

	

	List<Course> getActiveCourseList();

	void updateSubHeadingStatus(int subHeading_id, String subheadingStatus);



}
