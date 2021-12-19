package com.yht.controller;

import com.yht.entity.Course;
import com.yht.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private ICourseService courseService;
    @RequestMapping("/deleteCourse")
    public String deleteCourse(int id){
        courseService.deleteCourseById(id);
        return "redirect:/user/main";
    }
    @RequestMapping("/courseDetail")
    public String courseDetail(Model model, int id){
        Course course = courseService.findCourseById(id);
        model.addAttribute("course", course);
        return "courseDetail";
    }
    @RequestMapping("/updateCourse")
    public String updateCourse(Course course){
        courseService.updateCourseById(course);
        return "redirect:/user/main";
    }
    @RequestMapping("toAddCourse")
    public String toAddCourse(){
        return "addCourse";
    }
    @RequestMapping("addCourse")
    public String addCourse(Course course){
        courseService.addCourse(course);
        return "redirect:/user/main";
    }
}
