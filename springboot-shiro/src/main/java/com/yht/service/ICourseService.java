package com.yht.service;

import com.yht.entity.Course;

import java.util.List;

public interface ICourseService {
    List<Course> findAll();
    void addCourse(Course course);
    Course findCourseById(int id);
    void deleteCourseById(int id);
    void updateCourseById(Course course);
}
