package com.nosqllab.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Pan
 * @Date: 2019/11/18 20:37
 * @Description:
 **/
@Getter
@Setter
public class Course {
    private Long cid;
    private String name;
    private Long fcid;
    private Integer credit;
}
