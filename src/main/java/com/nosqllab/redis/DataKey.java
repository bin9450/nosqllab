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

    public static DataKey getCourse = new DataKey(60, "c");
    public static DataKey getStudent = new DataKey(60, "s");
    public static DataKey getTeacher = new DataKey(60, "t");
    public static DataKey isCourseOver = new DataKey(0, "go");
    public static DataKey getCourseStock = new DataKey(0, "gs");
    public static DataKey selCourse = new DataKey(0, "sc");
}
