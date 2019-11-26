package com.nosqllab.mapper;

import com.nosqllab.entity.Course;
import com.nosqllab.entity.Student;
import com.nosqllab.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    @Results(id="coutseMap", value={
            @Result(property = "courseStock", column = "course_stock")
    })
    @Select("select * from tb_course where cid =#{cid}")
    Course findCourseById(@Param("cid") Long cid);

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

// <trim prefix= 'set' suffixOverrides= ','> </trim>
    @Update("<script> UPDATE `tb_student` <set> " +
            " <if test='name!=null and name != \"\"  '>  `name` =#{name},  </if> " +
            " <if test='sex!=null and sex != \"\"  '>  `sex` = #{sex}, </if>" +
            " <if test='age!=null and age != \"\"  '> `age` = #{age}, </if>" +
            " <if test='birthday!=null and birthday != \"\"  '>  `birthday` = #{birthday},  </if>" +
            " <if test='dname!=null and dname != \"\" '>  `dname` = #{dname}, </if>" +
            " <if test='stuClass!=null and stuClass != \"\" '>  `class` = #{stuClass},  </if>" +
            " </set> WHERE `sid` =  #{sid}    </script>")
    int updateStudent(Student student);

    @Update("<script> UPDATE `tb_teacher` <set>  " +
            " <if test='name!=null and name != \"\"  '>  `name` =#{name}  </if> " +
            " <if test='sex!=null and sex != \"\"  '>  `sex` = #{sex} </if>" +
            " <if test='age!=null and age != \"\"  '> `age` = #{age} </if>" +
            " <if test='dname!=null and dname != \"\"  '> `dname` = #{dname} </if>" +
            "</set>  WHERE `tid` = #{tid}  </script>")
    int updateTeacher(Teacher teacher);

    @Update("<script> UPDATE `tb_course`  <set> " +
            " <if test='name!=null and name != \"\"  '>  `name` =#{name}  </if> " +
            " <if test='fcid!=null and fcid != \"\"  '>  `fcid` = #{fcid} </if>" +
            " <if test='credit!=null and credit != \"\"  '> `credit` = #{credit} </if>" +
            " <if test='courseStock!=null and courseStock != \"\"  '> `course_stock` = #{courseStock} </if>" +
            "</set>  WHERE `cid` = #{cid}  </script>")
    int updateCourse(Course course);

    @Results(id="courseMap", value={
            @Result(property = "courseStock", column = "course_stock")
    })

    @Select("SELECT tb_course.* ,tb_temp.num as num FROM tb_course," +
            "(SELECT DISTINCT cid,COUNT(*) AS num FROM tb_student_course GROUP BY cid) AS tb_temp\n" +
            " WHERE tb_course.`cid` = tb_temp.cid  LIMIT #{start}, #{end} ")
    List<Course> findSelCourse(@Param("start") int start, @Param("end") int end);
    @Select("SELECT tb_course.* ,score  FROM tb_course,(SELECT cid,MAX(score) AS score  FROM tb_student_course  " +
            "WHERE sid = #{sid}  ) AS tb_temp\n" +
            "WHERE tb_course.`cid` = tb_temp.cid")
    HashMap<String,Object> findMaxCourse(@Param("sid") long sid);

}
