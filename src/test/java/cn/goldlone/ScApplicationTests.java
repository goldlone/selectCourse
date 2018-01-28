package cn.goldlone;

import cn.goldlone.mapper.CourseMapper;
import cn.goldlone.mapper.StudentMapper;
import cn.goldlone.model.Course;
import cn.goldlone.po.DBCourse;
import cn.goldlone.po.DBCoursePlus;
import cn.goldlone.po.DBCoursePower;
import cn.goldlone.po.DBStudent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private StudentMapper sm;
	@Autowired
    private CourseMapper cm;

	@Test
	public void test() {
//		System.out.println(sm.getPassword("201502401086"));
//        System.out.println(sm.updatePassword("201502401086", "123456"));
//        System.out.println(sm.getAllStuInfo());
//        System.out.println(sm.getStudentInfoBySchoolNo(12));
//        System.out.println(sm.getStudentInfoBySchool());
//        System.out.println(sm.getStuInfo("201502401086"));
//        System.out.println(sm.getStuPower("201502401086"));



//        System.out.println(cm.getAllCourseInfo());
//        System.out.println(cm.getCourseInfo(1));

//        for(Course c: cm.getCourseList(0))
//            System.out.println(c);
    }

    @Test
    @Transactional
    public void addCourse() {
        DBCourse dbc = new DBCourse("数据结构ppp", 4);
        cm.addCourse(dbc);
        System.out.println(dbc);

        DBCoursePlus dbcp = new DBCoursePlus();
        dbcp.setCourseNo(dbc.getNo());
        dbcp.setStage(1);
        dbcp.setClassroom("文科三层报告厅");
        dbcp.setTeacher("张老师");
        dbcp.setStartDateTime(Timestamp.valueOf("2018-01-30 14:00:00"));
        dbcp.setEndDateTime(Timestamp.valueOf("2018-01-30 16:00:00"));
        DBCoursePlus dbcp2 = new DBCoursePlus();
        dbcp2.setCourseNo(dbc.getNo());
        dbcp2.setStage(2);
        dbcp2.setClassroom("文科三层报告厅");
        dbcp2.setTeacher("王老师");
        dbcp2.setStartDateTime(Timestamp.valueOf("2018-01-31 14:00:00"));
        dbcp2.setEndDateTime(Timestamp.valueOf("2018-01-31 16:00:00"));
        cm.addCoursePlus(dbcp);
        cm.addCoursePlus(dbcp2);

        DBCoursePower dbcpo = new DBCoursePower();
        dbcpo.setCourseNo(dbc.getNo());
        dbcpo.setPower(0);
        DBCoursePower dbcpo2 = new DBCoursePower();
        dbcpo2.setCourseNo(dbc.getNo());
        dbcpo2.setPower(2);
        DBCoursePower dbcpo3 = new DBCoursePower();
        dbcpo3.setCourseNo(dbc.getNo());
        dbcpo3.setPower(4);
        cm.addCoursePower(dbcpo);
        cm.addCoursePower(dbcpo2);
        cm.addCoursePower(dbcpo3);
    }


    @Test
    public void addStu() {
        DBStudent stu = new DBStudent();
        stu.setNo("201512345");
        stu.setName("李四");
        stu.setGender("男");
        stu.setAge(35);
        stu.setSchoolNo(14);
        stu.setPassword("123123");
        stu.setPower(3);

        sm.addStudent(stu);
    }
}
