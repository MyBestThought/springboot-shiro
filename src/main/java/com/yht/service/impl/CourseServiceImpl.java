package com.yht.service.impl;

import com.yht.dao.ICourseDao;
import com.yht.entity.Course;
import com.yht.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("courseService")
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private ICourseDao courseDao;
    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public void deleteCourseById(int id) {
        courseDao.deleteCourseById(id);
    }

    @Override
    public void updateCourseById(Course course) {
        courseDao.updateCourseById(course);
    }
}
