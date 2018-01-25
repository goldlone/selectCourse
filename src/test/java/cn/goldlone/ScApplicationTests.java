package cn.goldlone;

import cn.goldlone.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private StudentMapper sm;
	@Test
	public void test() {
//		System.out.println(sm.getPassword("201502401086"));
//        System.out.println(sm.updatePassword("201502401086", "123456"));
//        System.out.println(sm.getAllStuInfo());
//        System.out.println(sm.getStudentInfoBySchoolNo(17));
//        System.out.println(sm.getStudentInfoBySchool("计算机与信息技术学院"));
//        System.out.println(sm.getStuPower("201502401086"));


    }

}
