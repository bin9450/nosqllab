package com.nosqllab.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Pan
 * @Date: 2019/11/20 20:02
 * @Description:
 **/
@Getter
@Setter
public class StudentCourse {
    private Long scid;
    private Long sid;
    private Long cid;
    private Integer score;
    private Integer tid;
}
