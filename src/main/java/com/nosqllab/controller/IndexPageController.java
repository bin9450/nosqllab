package com.nosqllab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Pan
 * @Date: 2019/11/25 10:17
 * @Description:
 **/
@Controller
@RequestMapping("/")
public class IndexPageController {

    @RequestMapping("index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("web_admin/home.html")
    public String getHome(){
        return "home";
    }

    @RequestMapping("student")
    public String getStudent(){
        return "studentInfo";
    }

    @RequestMapping("teacher")
    public String getTeacher(){
        return "teacherInfo";
    }

    @RequestMapping("course")
    public String getCourse(){
        return "courseInfo";
    }

    @RequestMapping("addStudent")
    public String addStudent(){
        return "addStudent";
    }
    @RequestMapping("updateStudent")
    public String updateStudent(@RequestParam String sid){
        return "updateStudent";
    }

    @RequestMapping("addTeacher")
    public String addTeacher(){
        return "addTeacher";
    }
    @RequestMapping("updateTeacher")
    public String updateTeacher(@RequestParam String tid){
        return "updateTeacher";
    }

    @RequestMapping("addCourse")
    public String addCourse(){
        return "addCourse";
    }
    @RequestMapping("updateCourse")
    public String updateCourse(@RequestParam String cid){
        return "updateCourse";
    }

    @RequestMapping("selCourse")
    public String selCourse(){
        return "selCourse";
    }

    @RequestMapping("courseSel")
    public String courseSel(){
        return "courseSel";
    }
    //maxCourse
    @RequestMapping("maxCourse")
    public String maxCourse(String sid){
        return "maxCourse";
    }
}
