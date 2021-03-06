package com.nosqllab.controller;


import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.Teacher;
import com.nosqllab.result.Result;
import com.nosqllab.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:40
 * @Description:
 **/
@RestController
@RequestMapping("lab")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class LabController {
    @Autowired
    LabService labService;

    @GetMapping("findAllData")
    Result<Object> findAllData(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int pageSize,
                       @RequestParam(defaultValue = "1") int moudleCode){
        return labService.findAllData(page, pageSize, moudleCode);
    }

    @PostMapping("insertStudent")
    Result<Object> insertStudent(Student student){
        return labService.insertAllData(student);
    }

    @PostMapping("insertCourse")
    Result<Object> insertCourse(Course course){
        return labService.insertAllData(course);
    }

    @PostMapping("insertTeacher")
    Result<Object> insertTeacher(Teacher teacher){
        return labService.insertAllData(teacher);
    }

    @PostMapping("updateStudent")
    Result<Object> updateStudent(Student student){
        return labService.updateData(student);
    }

    @PostMapping("updateCourse")
    Result<Object> updateCourse(Course course){
        return labService.updateData(course);
    }

    @PostMapping("updateTeacher")
    Result<Object> updateTeacher(Teacher teacher){
        return labService.updateData(teacher);
    }

    @GetMapping("findSelCourse")
    Result<Object> findSelCourse(@RequestParam(defaultValue = "1") int page,  @RequestParam(defaultValue = "10")int pageSize){
        return labService.findSelCourse(page, pageSize);
    }

    @GetMapping("findMaxCourse")
    Result<Object> findMaxCourse(@RequestParam(defaultValue = "1") long sid){
        return labService.findMaxCourse(sid);
    }
}
