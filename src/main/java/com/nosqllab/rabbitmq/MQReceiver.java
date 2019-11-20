package com.nosqllab.rabbitmq;

import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.StudentCourse;
import com.nosqllab.mapper.LabMapper;
import com.nosqllab.mapper.SelectCourseMapper;
import com.nosqllab.redis.RedisService;
import com.nosqllab.service.SelectCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Pan
 * @Date: 2019/11/20 19:12
 * @Description:
 **/
@Service
public class MQReceiver {
    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    RedisService redisService;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    SelectCourseMapper selectCourseMapper;
    @Autowired
    LabMapper labMapper;

    @RabbitListener(queues=MQConfig.QUEUE)
    public void receive(String message){
        log.info("receive message:"+message);
        SelectCourseMessage m = RedisService.stringToBean(message, SelectCourseMessage.class);
        Student s = m.getStudent();
        long cid = m.getCid();

        Course course = labMapper.findCourseById(cid);
        int stock = course.getCourseStock();
        if(stock <= 0){
            return;
        }

        //判断重复选课
        StudentCourse studentCourse = selectCourseMapper.getStudentCourseById(cid, s.getSid());
        if(studentCourse != null) {
            return;
        }
        //减余量 写入SC
        selectCourseService.selectCourse(s,course);
    }
}
