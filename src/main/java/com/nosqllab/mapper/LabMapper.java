package com.nosqllab.mapper;

import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:11
 * @Description:
 **/
@Mapper
@Repository
public interface LabMapper {

    @Results(id="studentMap", value={
            @Result(property = "stuClass", column = "class")
    })
    @Select("select * from tb_student limit #{start}, #{end}")
    List<Student> findAllStudent(@Param("start") int start, @Param("end") int end);

    @Select("select * from tb_course limit #{start}, #{end}")
    List<Course> findAllCourse(@Param("start") int start, @Param("end") int end);

    @Select("select * from tb_teacher limit #{start}, #{end}")
    List<Teacher> findAllTeacher(@Param("start") int start, @Param("end") int end);

    @Insert("INSERT INTO tb_student(  `sid`,  `name`,  `sex`,  `age`, `birthday`,  `dname`,  `class`) \n" +
            "VALUES(#{sid},#{name},#{sex},#{age},#{birthday},#{dname},#{stuClass}) ")
    int insertStudent(Student student);

    @Insert("INSERT INTO tb_course (`cid`, `name`, `fcid`, `credit`) \n" +
            "VALUES (#{cid},#{name},#{fcid},#{credit}) ")
    int insertCourse(Course course);

    @Insert("INSERT INTO tb_teacher (`tid`, `name`, `sex`, `age`, `dname`) \n" +
            "VALUES (#{tid},#{name},#{sex},#{age},#{dname}) ")
    int insertTeacher(Teacher teacher);


}
