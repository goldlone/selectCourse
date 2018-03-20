//package cn.goldlone;
//
//import cn.goldlone.mapper.StudentMapper;
//import cn.goldlone.model.Admin;
//import cn.goldlone.model.Student;
//import cn.goldlone.po.DBAdmin;
//import cn.goldlone.po.DBFeedback;
//import cn.goldlone.po.DBStudent;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class StudentTest {
//
//    @Autowired
//    private StudentMapper sm;
//
//    /**
//     * 录入学员基本信息
//     */
//    @Test
//    public void addDBStu() {
//        DBStudent stu = new DBStudent();
//        stu.setNo("123");
//        stu.setName("学员2");
//        stu.setSchoolNo(2);
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
//        stu.setPassword(DigestUtils.sha256Hex("123"));
//        sm.addDBStudent(stu);
//    }
//
//    /**
//     * 录入学员信息
//     */
//    @Test
//    public void addStu(){
//        Student stu1 = new Student();
//        stu1.setNo("456");
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
//        stu1.setPassword(DigestUtils.sha256Hex("456"));
//        sm.addStudent(stu1);
//    }
//
//    /**
//     * 添加管理员
//     */
//    @Test
//    public void addAdmin() {
//        Admin admin1 = new Admin("456", "管理员1", "文学院党委", DigestUtils.sha256Hex("456"), 2);
//        sm.addAdmin(admin1);
//        DBAdmin admin2 = new DBAdmin("123", "超级管理员1", 1, DigestUtils.sha256Hex("123"), 1);
//        sm.addDBAdmin(admin2);
//    }
//
//    @Test
//    public void feedback(){
//        sm.feedback("123", "第四条反馈信息");
//
//        List<DBFeedback> list = sm.getFeedback();
//        for (DBFeedback df: list)
//            System.out.println(df);
//
//        sm.dealFeedback(list.get(0).getId(), "123");
//    }
//
//
//
//
//}
