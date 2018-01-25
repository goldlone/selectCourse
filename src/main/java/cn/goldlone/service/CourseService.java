package cn.goldlone.service;

import java.io.IOException;

/**
 * Created by CN on 2017/12/18.
 */
public interface CourseService {
    // 获取可选课的课程列表
    public String getCourseSet() throws IOException;
    // 查询座位状况
    public String getSeatStatus() throws IOException;
    // 查询选课的情况
    public String getSeatStatusByMaster() throws IOException;
    // 学生选课选座
    public String selectCourse() throws IOException;
    // 取消选课
    public String cancelSelectCourse() throws IOException;
    // 查询已获得学时
    public String getAcquireTime() throws IOException;
    // 查看已选课程
    public String getSelectCourse() throws IOException;
    // 发布课程
    public String publicCourse() throws IOException;
    // 修改课程基本信息(课程名，教室，教师，学分)
    public String updateBaseCourseInfo() throws IOException;
    // 修改选课课程权限
    public String updateCoursePowerInfo() throws IOException;
    // 修改某期的课程信息
    public String updateCoursePlusInfo() throws IOException;
    // 反馈哪些人上课了
    public String updateSomeoneCome() throws IOException;

}
