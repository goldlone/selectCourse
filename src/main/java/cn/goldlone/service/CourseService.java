package cn.goldlone.service;

import cn.goldlone.model.Result;
import cn.goldlone.po.DBCourse;
import cn.goldlone.po.DBCoursePlus;
import cn.goldlone.po.DBCoursePower;

import java.util.List;

/**
 * Created by CN on 2017/12/18.
 */
public interface CourseService {

    /**
     * 发布课程
     * @return
     */
    public Result publicCourse();

    /**
     * 添加课程基本信息
     * @param course
     * @return
     */
    public Result addCourse(DBCourse course);

    /**
     * 添加课程权限
     * @param coursePower
     * @return
     */
    public Result addCoursePower(DBCoursePower coursePower);

    /**
     * 添加课程其余期的信息
     * @param coursePlus
     * @return
     */
    public Result addCoursePlus(DBCoursePlus coursePlus);

    /**
     * 获取全部的课程信息
     * @return
     */
    public Result getAllCourseInfo();

    /**
     * 修改课程基本信息(课程名，学分)
     * @param course
     * @return
     */
    public Result updateBaseCourse(DBCourse course);

    /**
     * 修改课程权限
     * @param courseNo
     * @param powers
     * @return
     */
    public Result updateCoursePower(int courseNo, List<Integer> powers);

    /**
     * 修改某期的课程信息
     * @param coursePlus
     * @return
     */
    public Result updateCoursePlus(DBCoursePlus coursePlus);

    /**
     * 根据权限获取可选课程列表
     * @param power
     * @return
     */
    public Result getCourseList(int power);

    /**
     * 查询某期课程座位选择状况
     * @param courseNo
     * @param stage
     * @return
     */
    public Result getSeatStatus(int courseNo, int stage);

    /**
     * 学生选课选座
     * @param stuNo
     * @param courseNo
     * @param stage
     * @param seatNo
     * @return
     */
    public Result selectCourse(String stuNo, int courseNo, int stage, int seatNo);

    /**
     * 取消选课
     * @param courseNo
     * @param stuNo
     * @return
     */
    public Result cancelSelectCourse(int courseNo, int stage, String stuNo);

    /**
     * 学员获取自己的选课状况
     * @param stuNo
     * @return
     */
    public Result getMyCourseSeat(String stuNo);

    /**
     * 管理员查询某节某期课程的选座状况
     * @param courseNo
     * @param stage
     * @return
     */
    public Result getSelectCourseInfoByMaster(int courseNo, int stage);

    /**
     * 查看某个学员已选课程
     * @param stuNo
     * @return
     */
    public Result getSelectCourse(String stuNo);

    /**
     * 反馈某期课程到课人员信息（单个人的信息）
     * @param list
     * @param courseNo
     * @return
     */
    public Result updateSomeoneCome(List<String> list, int courseNo);

    /**
     * 获取某个学生的已获得学时总额
     * @param stuNo
     * @return
     */
    public Result getAcquireTime(String stuNo);

}
