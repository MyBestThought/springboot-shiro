package com.yht.controller;

import com.yht.dao.ICourseDao;
import com.yht.entity.Course;
import com.yht.entity.User;
import com.yht.service.ICourseService;
import com.yht.service.IUserService;
import com.yht.utils.RoleUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICourseService courseService;
    @RequestMapping("/main")
    public String hello(Model model){
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "main";
    }
    @RequestMapping("/login")
    public String login(Model model, User user, @RequestParam String code, HttpSession session){
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        user.setIdentify(RoleUtils.getRoleByKey(Integer.parseInt(user.getIdentify())));
        User searchUser = userService.findByUsername(user.getUsername());
        if(searchUser.getIdentify().equals(user.getIdentify())){
            //验证码
            try{
                if(random.equals(code)){
                    Subject subject = SecurityUtils.getSubject();
                    subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
                    List<Course> courseList = courseService.findAll();
                    model.addAttribute("courseList", courseList);
                    return "main";
                }else{
                    throw new RuntimeException("验证码错误");
                }
            }catch (UnknownAccountException e){
                e.printStackTrace();
                System.out.println("用户名错误");
            }catch (IncorrectCredentialsException e){
                e.printStackTrace();
                System.out.println("密码错误");
            }

        }else{
            System.out.println("身份错误");
            return "index";
        }

        return "index";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    @RequestMapping("/register")
    public String register(User user){
        userService.addUser(user);
        return "/index";
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }
}
