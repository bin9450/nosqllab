package com.nosqllab.rabbitmq;

import com.nosqllab.entity.Student;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Pan
 * @Date: 2019/11/20 19:15
 * @Description:
 **/
@Getter
@Setter
public class SelectCourseMessage {
    private Student student;
    private long cid;
}
