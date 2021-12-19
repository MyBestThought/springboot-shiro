package com.yht.dao;

import com.yht.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ICourseDao {
    List<Course> findAll();
    void addCourse(Course course);
    Course findCourseById(int id);
    void deleteCourseById(int id);
    void updateCourseById(Course course);
}
