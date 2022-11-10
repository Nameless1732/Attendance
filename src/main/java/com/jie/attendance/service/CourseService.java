package com.jie.attendance.service;

import com.jie.attendance.entity.Course;
import com.jie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface CourseService {
    PageBean<Course> queryPage(Map<String, Object> paramMap);

    int addCourse(Course course);

    int deleteCourse(List<Integer> ids);

    int editCourse(Course course);

    List<Course> getCourseById(List<Integer> ids);
}
