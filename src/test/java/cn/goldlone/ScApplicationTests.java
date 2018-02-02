package cn.goldlone;

import cn.goldlone.mapper.CourseMapper;
import cn.goldlone.mapper.StudentMapper;
import cn.goldlone.model.Course;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.po.*;
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
	public void testStu() {
//	    // 获取学员密码
//        LoginInfo info = sm.getStuPassword("123");
//        System.out.println(info);
//        // 获取基层党组织管理员密码
//        LoginInfo info1 = sm.getAdminPassword("123");
//        System.out.println(info1);
        // 修改学员登录密码
        sm.updateStuPassword("123", "123", "456");
        // 修改管理员登录密码
        sm.updateAdminPassword("123", "123", "456");
        // 获取全部身份权限
        for(DBPowers p: sm.getPowers())
            System.out.println(p);
        // 获取所有基层党组织信息
        for(DBSchool s: sm.getSchools())
            System.out.println(s);
        // 根据基层党委名查询编号
        System.out.println(sm.getSchoolNoByName("计算机与信息技术学院党委（大数据学院党委）"));


    }


	@Test
	public void test() {
//		System.out.println(sm.getStuPassword("201502401086"));
//        System.out.println(sm.updateStuPassword("201502401086", "123456"));
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
    public void testTime() {
        System.out.println("111111");
        Course course = cm.getCourseInfo(12, 1);
        System.out.println(course.getStartDateTime().after(new Timestamp(System.currentTimeMillis())));
    }

    @Test
//    @Transactional
    public void addCourse() {
        DBCourse dbc = new DBCourse("数据结构", 2);
        cm.addCourse(dbc);
        System.out.println(dbc);

        DBCoursePlus dbcp = new DBCoursePlus();
        dbcp.setCourseNo(dbc.getNo());
        dbcp.setStage(1);
        dbcp.setClassroom("文科三层报告厅");
        dbcp.setTeacher("张老师");
        dbcp.setStartDateTime(Timestamp.valueOf("2018-02-14 14:00:00"));
        dbcp.setEndDateTime(Timestamp.valueOf("2018-02-14 16:00:00"));
        DBCoursePlus dbcp2 = new DBCoursePlus();
        dbcp2.setCourseNo(dbc.getNo());
        dbcp2.setStage(2);
        dbcp2.setClassroom("文科三层报告厅");
        dbcp2.setTeacher("王老师");
        dbcp2.setStartDateTime(Timestamp.valueOf("2018-02-15 14:00:00"));
        dbcp2.setEndDateTime(Timestamp.valueOf("2018-02-15 16:00:00"));
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
        dbcpo3.setPower(3);
        cm.addCoursePower(dbcpo);
        cm.addCoursePower(dbcpo2);
        cm.addCoursePower(dbcpo3);

    }

    @Test
//    @Transactional
    public void addCourse1() {
        DBCourse dbc = new DBCourse("操作系统", 3);
        cm.addCourse(dbc);
        System.out.println(dbc);

        DBCoursePlus dbcp = new DBCoursePlus();
        dbcp.setCourseNo(dbc.getNo());
        dbcp.setStage(1);
        dbcp.setClassroom("文科三层报告厅");
        dbcp.setTeacher("李老师");
        dbcp.setStartDateTime(Timestamp.valueOf("2018-02-14 14:00:00"));
        dbcp.setEndDateTime(Timestamp.valueOf("2018-02-14 16:00:00"));
        DBCoursePlus dbcp2 = new DBCoursePlus();
        dbcp2.setCourseNo(dbc.getNo());
        dbcp2.setStage(2);
        dbcp2.setClassroom("文科三层报告厅");
        dbcp2.setTeacher("孙老师");
        dbcp2.setStartDateTime(Timestamp.valueOf("2018-02-15 14:00:00"));
        dbcp2.setEndDateTime(Timestamp.valueOf("2018-02-15 16:00:00"));
        DBCoursePlus dbcp3 = new DBCoursePlus();
        dbcp3.setCourseNo(dbc.getNo());
        dbcp3.setStage(3);
        dbcp3.setClassroom("文科三层报告厅");
        dbcp3.setTeacher("白老师");
        dbcp3.setStartDateTime(Timestamp.valueOf("2018-02-16 14:00:00"));
        dbcp3.setEndDateTime(Timestamp.valueOf("2018-02-16 16:00:00"));
        cm.addCoursePlus(dbcp);
        cm.addCoursePlus(dbcp2);
        cm.addCoursePlus(dbcp3);

        DBCoursePower dbcpo3 = new DBCoursePower();
        dbcpo3.setCourseNo(dbc.getNo());
        dbcpo3.setPower(3);
        DBCoursePower dbcpo4 = new DBCoursePower();
        dbcpo4.setCourseNo(dbc.getNo());
        dbcpo4.setPower(4);
        DBCoursePower dbcpo5 = new DBCoursePower();
        dbcpo5.setCourseNo(dbc.getNo());
        dbcpo5.setPower(5);
        cm.addCoursePower(dbcpo3);
        cm.addCoursePower(dbcpo4);
        cm.addCoursePower(dbcpo5);
    }




    @Test
    public void addStu() {
        DBStudent stu = new DBStudent();
        stu.setNo("201512345");
        stu.setName("李四");
        stu.setGender("男");
        stu.setSchoolNo(14);
        stu.setPassword("123123");
        stu.setPower(3);

        sm.addStudent(stu);
    }
}
