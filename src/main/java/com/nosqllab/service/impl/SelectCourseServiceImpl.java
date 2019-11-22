package com.nosqllab.service.impl;

import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.StudentCourse;
import com.nosqllab.mapper.SelectCourseMapper;
import com.nosqllab.redis.DataKey;
import com.nosqllab.redis.RedisService;
import com.nosqllab.service.SelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Pan
 * @Date: 2019/11/20 20:13
 * @Description:
 **/
@Service
public class SelectCourseServiceImpl implements SelectCourseService{
    @Autowired
    RedisService redisService;
    @Autowired
    SelectCourseMapper selectCourseMapper;


    @Override
    @Transactional
    public void selectCourse(Student student, Course course) {
        int success = selectCourseMapper.reduceCount(course.getCid());
        if (success != 0){
            //下订单 写入秒杀订单
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setCid(course.getCid());
            studentCourse.setSid(student.getSid());
            redisService.set(DataKey.selCourse, String.valueOf(course.getCid()+student.getSid()), "1");
            selectCourseMapper.insertStudentCourse(studentCourse);
        }else {
            setCourseOver(course.getCid());
            return ;
        }
    }

    @Override
    public long getSeckillResult(long cid, long sid){
        StudentCourse studentCourse = selectCourseMapper.getStudentCourseById(cid, sid);
        if (studentCourse != null){
            return studentCourse.getScid();
        }else{
            boolean isOver = getCourseOver(cid);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    @Override
    public void setCourseOver(Long cid) {
        redisService.set(DataKey.isCourseOver, ""+cid, true);
    }

    @Override
    public boolean getCourseOver(long cid) {
        return redisService.exists(DataKey.isCourseOver, ""+cid);
    }
}
