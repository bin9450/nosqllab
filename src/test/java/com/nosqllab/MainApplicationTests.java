package com.nosqllab;

import com.nosqllab.entity.Student;
import com.nosqllab.mapper.LabMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	@Autowired
	LabMapper labMapper;
	@Test
	public void contextLoads() {
	    System.out.println("test");
		Student student =new Student();
		student.setSid((long) 123);
		student.setSex("女");
		labMapper.updateStudent(student);
	}

}
