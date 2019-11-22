package com.nosqllab.mapper;

import com.nosqllab.entity.StudentCourse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: Pan
 * @Date: 2019/11/20 20:26
 * @Description:
 **/
@Repository
@Mapper
public interface SelectCourseMapper {


    @Insert("INSERT INTO `tb_student_course` ( `sid`, `cid`, `score`,`tid`) \n" +
            "VALUES(#{sid},#{cid},#{score},#{tid}) ")
    @SelectKey(keyColumn = "scid", keyProperty = "scid", resultType = long.class, before = false, statement = "select last_insert_id()")
    Long insertStudentCourse(StudentCourse studentCourse);

    @Update("UPDATE  `tb_course` \n" +
            "SET `course_stock` = course_stock - 1 \n" +
            "WHERE `cid` = #{cid} ")
    int reduceCount(@Param("cid") Long cid);

    @Select("select * from tb_student_course where cid = #{cid} and sid = #{sid}")
    StudentCourse getStudentCourseById(@Param("cid") long cid, @Param("sid") long sid);
}
