package com.nosqllab.service.impl;



import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.Teacher;
import com.nosqllab.mapper.LabMapper;
import com.nosqllab.redis.DataKey;
import com.nosqllab.redis.RedisService;
import com.nosqllab.result.CodeMsg;
import com.nosqllab.result.Result;
import com.nosqllab.service.LabService;
import com.nosqllab.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Pan
 * @Date: 2019/11/17 21:15
 * @Description:
 **/
@Service
public class LabServiceImpl implements LabService {
    @Autowired
    LabMapper labMapper;
    @Autowired
    FileUtil fileUtil;
    @Autowired
    RedisService redisService;

    @Override
    public Result<Object> findAllData(int page, int pageSize, int moudleCode) {
        List<?> resultList = new ArrayList<>();
        String keyCache = String.valueOf(page) + "&" + String.valueOf(pageSize) + "&" +String.valueOf(moudleCode);
        if (moudleCode == 1){
             resultList = redisService.get(DataKey.getStudent, keyCache , List.class);
            if (resultList == null){
                resultList = labMapper.findAllStudent((page-1)*pageSize, pageSize);
                redisService.set(DataKey.getStudent, keyCache, resultList);
            }
        }else if (moudleCode == 2){
            resultList = redisService.get(DataKey.getTeacher, keyCache , List.class);
            if (resultList == null){
                resultList = labMapper.findAllTeacher((page-1)*pageSize, pageSize);
                redisService.set(DataKey.getTeacher, keyCache, resultList);
            }
        }else if (moudleCode == 3){
            resultList = redisService.get(DataKey.getCourse, keyCache , List.class);
            if (resultList == null){
                resultList = labMapper.findAllCourse((page-1)*pageSize, pageSize);
                redisService.set(DataKey.getTeacher, keyCache, resultList);
            }
        }
       if (resultList.size() != 0){
            return Result.success(CodeMsg.SERVICE_SELECT_OK,resultList);
        }else {
           return Result.error(CodeMsg.SERVICE_SELECT_ERROR);
        }
    }

    @Override
    public <T> Result<Object> insertAllData(T data) {
        if (data == null) {
            return null;
        }
        Class<?> clazz = data.getClass();
        int resultInsert = 0;
       if(clazz == Student.class){
           resultInsert = labMapper.insertStudent((Student) data);
       }else if (clazz == Teacher.class){
           resultInsert = labMapper.insertTeacher((Teacher) data);
       }else {
           resultInsert = labMapper.insertCourse((Course) data);
           System.out.println(resultInsert);
       }
       if (resultInsert == 0){
           return Result.error(CodeMsg.SERVICE_INSERT_ERROR);
       }else {
           return Result.success(CodeMsg.SERVICE_INSERT_OK);
       }
    }


}
