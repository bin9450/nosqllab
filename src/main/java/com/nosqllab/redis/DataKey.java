package com.nosqllab.redis;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:40
 * @Description:
 **/
public class DataKey extends BasePrefix {

    private DataKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static DataKey getCourse = new DataKey(30, "c");
    public static DataKey getStudent = new DataKey(30, "s");
    public static DataKey getTeacher = new DataKey(30, "t");
    public static DataKey isCourseOver = new DataKey(0, "go");
    public static DataKey getCourseStock = new DataKey(0, "gs");
    public static DataKey selCourse = new DataKey(20, "sc");
    public static DataKey findData = new DataKey(60, "fd");
}
