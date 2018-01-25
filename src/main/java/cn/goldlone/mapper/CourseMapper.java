package cn.goldlone.mapper;

import cn.goldlone.model.Course;
import cn.goldlone.model.CourseSeat;
import cn.goldlone.po.DBCourse;
import cn.goldlone.po.DBCoursePlus;
import cn.goldlone.po.DBCoursePower;

import java.util.List;

/**
 * Created by CN on 2017/12/17.
 */
public interface CourseMapper {
    // 发布一个课程
    // 添加课程基本信息
    public int addCourse(DBCourse course);
    // 添加课程权限
    public int addCoursePower(DBCoursePower coursePower);
    // 添加课程其余期的信息
    public int addCoursePlus(DBCoursePlus coursePlus);

    // 获取课程的基本信息
    public Course getCourseInfo(int courseNo);
    // 获取全部的课程信息
    public List<Course> getAllCourseInfo();
    // 获取该课程的选课权限
    public List<Integer> getCoursePowers(int courseNo);
    // 修改课程基本信息(课程名，教室，教师，学分)
    public int updateBaseCourse(DBCourse course);
    // 修改选课课程权限
    public int updateCoursePower(DBCoursePower coursePower);
    // 修改某期的课程信息
    public int updateCoursePlus(DBCoursePlus coursePlus);
    // 根据权限获取可选课程列表
    public List<Course> getCourseList(int power);
    // 学生选课选座
//    public int selectCourse(@Param("stuNo") String stuNo, @Param("courseNo") int courseNo, @Param("stage") int stage, @Param("seatNo") int seatNo);
//    // 取消选课
//    public int cancelSelectCourse(@Param("courseNo") int courseNo, @Param("stuNo") String stuNo);
//    // 查询某期课程座位选择状况
//    public List<Integer> getSeatStatus(@Param("courseNo") int courseNo, @Param("stage") int stage);
//    // 管理员查询某节某期课程的选座状况
//    public List<CourseSeat> getSelectCourseInfoByMaster(@Param("courseNo") int courseNo, @Param("stage") int stage);
//    // 查看某个学员已选课程
//    public List<CourseSeat> getSelectCourse(String stuNo);
//    // 获取某个学生的已获得学时总额
//    public int getAcquireTime(String stuNo);
//    // 反馈某期课程到课人员信息（单个人的信息）
//    public int updateSomeoneCome(@Param("stuNo") String stuNo, @Param("courseNo") int courseNo);

}
