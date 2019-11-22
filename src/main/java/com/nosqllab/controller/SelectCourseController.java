package com.nosqllab.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.StudentCourse;
import com.nosqllab.mapper.LabMapper;
import com.nosqllab.mapper.SelectCourseMapper;
import com.nosqllab.rabbitmq.MQSender;
import com.nosqllab.rabbitmq.SelectCourseMessage;
import com.nosqllab.redis.DataKey;
import com.nosqllab.redis.RedisService;
import com.nosqllab.result.CodeMsg;
import com.nosqllab.result.Result;
import com.nosqllab.service.SelectCourseService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Pan
 * @Date: 2019/11/21 14:51
 * @Description:
 **/
@RestController
@RequestMapping("selectCourse")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class SelectCourseController implements InitializingBean {
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    RedisService redisService;
    @Autowired
    LabMapper labMapper;
    @Autowired
    SelectCourseMapper selectCourseMapper;
    @Autowired
    MQSender mqSender;

    //基于令牌桶算法的限流实现类
    RateLimiter rateLimiter = RateLimiter.create(10);

    //做标记，判断该课是否被处理过了
    private HashMap<Long, Boolean> localOverMap = new HashMap<Long, Boolean>();

    @GetMapping("doSelect")
    public Result<Integer> doSelect(@RequestParam Long sid, @RequestParam Long cid){
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            return  Result.error(CodeMsg.ACCESS_LIMIT_REACHED);
        }
        boolean over = localOverMap.get(cid);
        if (over) {
            return Result.error(CodeMsg.SELCOURSE_OVER);
        }

        long stock = redisService.decr(DataKey.getCourseStock, "" + cid);
        if (stock < 0){
            afterPropertiesSet();
            long stock2 = redisService.decr(DataKey.getCourseStock, "" + cid);
            if(stock2 < 0){
                localOverMap.put(cid, true);
                return Result.error(CodeMsg.SELCOURSE_OVER);
            }
        }
        String isSel = redisService.get(DataKey.selCourse, String.valueOf(cid+sid), String.class);
        if ("1".equals(isSel)){
            return Result.error(CodeMsg.REPEATE_SELECT);
        }
        StudentCourse order = selectCourseMapper.getStudentCourseById(cid, sid);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_SELECT);
        }

        SelectCourseMessage selectCourseMessage = new SelectCourseMessage();
        Student student = new Student();
        student.setSid(sid);
        selectCourseMessage.setCid(cid);
        selectCourseMessage.setStudent(student);
        mqSender.sendMessage(selectCourseMessage);
        return Result.success(0);
    }

    @Override
    public void afterPropertiesSet() {
        List<Course> courseList = labMapper.findAllCourse(0,150);
        if (courseList == null) {
            return;
        }
        courseList.stream().filter(course ->
            course.getCourseStock() > 0
        ).forEach((course)->{
            redisService.set(DataKey.getCourseStock, "" + course.getCid(), course.getCourseStock());
            localOverMap.put(course.getCid(), false);
        });
    }

    @GetMapping("selCourse")
    public Result<Long> selCourselResult(@RequestParam Long sid, @RequestParam Long cid) {
        Long scid = selectCourseService.getSeckillResult(cid, sid);
        return Result.success(scid);
    }

}
