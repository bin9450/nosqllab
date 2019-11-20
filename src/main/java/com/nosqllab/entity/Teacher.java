package com.nosqllab.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Pan
 * @Date: 2019/11/18 20:46
 * @Description:
 **/
@Getter
@Setter
public class Teacher {
    private Long tid;
    private String name;
    private String sex;
    private Integer age;
    private String dname;
}
