package com.nosqllab.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:00
 * @Description:
 **/
@Getter
@Setter
public class Student {
    private Long sid;
    private String name;
    private String sex;
    private Integer age;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String dname;
    private Integer stuClass;
}
