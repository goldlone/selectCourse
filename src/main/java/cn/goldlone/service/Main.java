package cn.goldlone.service;

import cn.goldlone.mapper.CourseMapper;
import cn.goldlone.mapper.StudentMapper;
import cn.goldlone.model.LoginInfo;
//import cn.goldlone.utils.MybatisUtils;

/**
 * Created by CN on 2017/12/14.
 */
public class Main {


    public static void main(String[] args) {



//        SqlSession sqlSession = null;
//        try {
//            sqlSession = MybatisUtils.openSqlSession();
//            StudentMapper sm = sqlSession.getMapper(StudentMapper.class);

//            // 登录/修改密码
//            LoginInfo li = sm.getPassword("789");
//            System.out.println(li);
//            li.setPassword("456");
//            int res = sm.updatePassword(li);
//            System.out.println(res);
//            sqlSession.commit();
//            System.out.println(res);
//            System.out.println(li.toString());

//            // 添加学员
//            DBStudent s1 = new DBStudent();
//            s1.setAge(18);
//            s1.setGender("男");
//            s1.setGrade(2015);
//            s1.setMajor("计科");
//            s1.setName("郑超");
//            s1.setNo("456");
//            s1.setPassword(DigestUtils.sha256Hex("456"));
//            s1.setPower(1);
//            s1.setSchool("计信");
//            sm.addStudent(s1);
//            sqlSession.commit();

//            // 获取用户权限
//            int power = sm.getStuPower("123");
//            System.out.println(power);
//            power = sm.getStuPower("456");
//            System.out.println(power);

//            // 获取全部学生信息
//            List<Student> list = sm.getAllStuInfo();
//            for(Student s: list)
//                System.out.println(s);

//            // 根据学号查询某个学生的详细信息
//            Student s = sm.getStuInfo("123");
//            System.out.println(s);
//
//            // 修改用户信息
//            s.setName("张三");
//            s.setGrade(2001);
//            s.setMajor("经管");
//            DBStudent s1 = new DBStudent(s);
//            sm.updateStuInfo(s1);
//            sqlSession.commit();
//            s = sm.getStuInfo("123");
//            System.out.println(s);

//            CourseMapper cm = sqlSession.getMapper(CourseMapper.class);
//            // 发布课程
//            DBCourse c = new DBCourse();
//            c.setClassroom("文科三层报告厅");
//            c.setName("数据结构");
//            c.setTeacher("吕老师");
//            c.setTime(2);
//            cm.addCourse(c);
//            DBCoursePower cp = new DBCoursePower();
//            cp.setCourseNo(c.getNo());
//            cp.setPower(0);
//            cm.addCoursePower(cp);
//            cp.setPower(1);
//            cm.addCoursePower(cp);
//            cp.setPower(5);
//            cm.addCoursePower(cp);
//            DBCoursePlus cpp = new DBCoursePlus();
//            cpp.setCourseNo(c.getNo());
//            cpp.setStage(1);
//            cpp.setStartSelectDate(new Timestamp(System.currentTimeMillis()));
//            cpp.setEndSelectDate(new Timestamp(System.currentTimeMillis()));
//            cm.addCoursePlus(cpp);
//            sqlSession.commit();


            // 获取课程信息
//            Course c = cm.getCourseInfo(2);
//            System.out.println(c);
            // 获取全部课程信息
//            List<Course> list = cm.getAllCourseInfo();
//            for(Course c: list)
//                System.out.println(c);
            // 获取该课程的选课权限
//            List<Integer> list = cm.getCoursePowers(2);
//            for(int i: list)
//                System.out.println(i);
            // 修改课程基本信息
//            cm.updateBaseCourse()

            // 根据权限获取可选课程列表
//            List<Course> list = cm.getCourseList(0);
//            for(Course c: list)
//                System.out.println(c);
            // 选课选座
//            System.out.println(cm.selectCourse("123", 2, 1, 7));
//            sqlSession.commit();
            // 取消选课
//            System.out.println(cm.cancelSelectCourse(1, "123"));
//            sqlSession.commit();
//            // 获取课程列表
//            List<Course> list = cm.getCourseList(0);
//            for(Course c: list)
//                System.out.println(c);
            // 查询某期课程座位选择状况
//            List<Integer> list = cm.getSeatStatus(1, 1);
//            for(Integer c: list)
//                System.out.println(c);
            // 管理员查询某节某期课程的选座状况
//            List<CourseSeat> list = cm.getSelectCourseInfoByMaster(2, 1);
//            for(CourseSeat s : list)
//                System.out.println(s);
            // 查看某个学员已选课程
//            List<CourseSeat> list = cm.getSelectCourse("123");
//            for(CourseSeat s: list)
//                System.out.println(s);
            // 反馈某期课程到课人员信息（单个人的信息）
//            System.out.println(cm.updateSomeoneCome("123", 2));
//            sqlSession.commit();
            // 获取某个学生的已获得学时总额
//            System.out.println(cm.getAcquireTime("123"));

//        } catch (Exception e) {
//            e.printStackTrace();
//            if(sqlSession!=null)
//                sqlSession.rollback();
//        } finally {
//            if(sqlSession != null)
//                sqlSession.close();
//        }


    }
}
