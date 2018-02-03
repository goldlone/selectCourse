package cn.goldlone;

import cn.goldlone.mapper.CourseMapper;
import cn.goldlone.mapper.StudentMapper;
import cn.goldlone.model.Admin;
import cn.goldlone.model.Course;
import cn.goldlone.model.Student;
import cn.goldlone.po.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;

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
//        // 修改学员登录密码
//        sm.updateStuPassword("123", "123", "456");
//        // 修改管理员登录密码
//        sm.updateAdminPassword("123", "123", "456");
//        // 获取全部身份权限
//        for(DBPowers p: sm.getPowers())
//            System.out.println(p);
//        // 获取所有基层党组织信息
//        for(DBSchool s: sm.getSchools())
//            System.out.println(s);
//        // 根据基层党委名查询编号
//        System.out.println(sm.getSchoolNoByName("计算机与信息技术学院党委（大数据学院党委）"));
        // 录入学员基本信息
//        DBStudent stu = new DBStudent();
//        stu.setNo("456");
//        stu.setName("学员2");
//        stu.setSchoolNo(1);
//        stu.setGender("女");
//        stu.setNation("汉");
//        stu.setBirth(Date.valueOf("1997-06-02"));
//        stu.setType("研究生");
//        stu.setGrade(2017);
//        stu.setPosition("党支部");
//        stu.setApplyDate(Date.valueOf("2015-01-01"));
//        stu.setBeActivistDate(Date.valueOf("2015-01-02"));
//        stu.setBeDevelopDate(Date.valueOf("2015-01-03"));
//        stu.setPower(2);
//        stu.setPassword(DigestUtils.sha256Hex("456"));
//        sm.addDBStudent(stu);
//        // 录入学员信息
//        Student stu1 = new Student();
//        stu1.setNo("123");
//        stu1.setName("学员1");
//        stu1.setSchool("文学院党委");
//        stu1.setGender("男");
//        stu1.setNation("汉");
//        stu1.setBirth(Date.valueOf("1997-06-02"));
//        stu1.setType("本科生");
//        stu1.setGrade(2016);
//        stu1.setPosition("无");
//        stu1.setApplyDate(Date.valueOf("2015-01-01"));
//        stu1.setBeActivistDate(Date.valueOf("2015-01-02"));
//        stu1.setBeDevelopDate(Date.valueOf("2015-01-03"));
//        stu1.setIdentity("入党积极分子");
//        stu1.setPassword(DigestUtils.sha256Hex("123"));
//        sm.addStudent(stu1);
//        Admin admin1 = new Admin("456", "管理员2", "文学院党委", DigestUtils.sha256Hex("456"), 1);
//        sm.addAdmin(admin1);
//        DBAdmin admin2 = new DBAdmin("789", "超级管理员1", 37, DigestUtils.sha256Hex("789"), 0);
//        sm.addDBAdmin(admin2);
        for(Admin a: sm.getAllAdminInfo())
            System.out.println(a);
        System.out.println("----");
        for(Student s: sm.getAllStuInfo())
            System.out.println(s);
        System.out.println("----");
        for(Student s: sm.getStudentInfoBySchoolNo(1))
            System.out.println(s);
        System.out.println("----");
        System.out.println(sm.getStuInfo("123"));
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

        sm.addDBStudent(stu);
    }
}
