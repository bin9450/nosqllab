package com.nosqllab.service;

import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.StudentCourse;

/**
 * @Author: Pan
 * @Date: 2019/11/20 19:57
 * @Description:
 **/
public interface SelectCourseService {
    void selectCourse(Student student, Course course);
    void setCourseOver(Long cid);
    boolean getCourseOver(long cid);
}
