package com.haleyysz.discussion.services;

import com.haleyysz.discussion.models.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseService {

    String[] ids = {"1", "2", "3", "4"};
    String[] courseNames = {"CS - 120 Java", "CS - 741 Software Engineering", "CS 743 - Software Verification and Validation", "CS 451/551 - User Interface Design"};

    public CourseService() {

    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            Course course = new Course();
            course.setId(ids[i]);
            course.setCourseName(courseNames[i]);
            courses.add(course);
        }
        return courses;
    }


}
