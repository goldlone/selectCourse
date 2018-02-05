package cn.goldlone.service.impl;

import cn.goldlone.mapper.CourseMapper;
import cn.goldlone.model.Course;
import cn.goldlone.model.CourseSeat;
import cn.goldlone.model.Result;
import cn.goldlone.po.DBCourse;
import cn.goldlone.po.DBCoursePlus;
import cn.goldlone.po.DBCoursePower;
import cn.goldlone.service.CourseService;
import cn.goldlone.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by CN on 2017/12/20.
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper cm;

    @Override
    public Result publicCourse() {

        return null;
    }

    /**
     * 添加课程基本信息
     * @param course
     * @return
     */
    @Override
    public Result addCourse(DBCourse course) {
//        cm.addCourse(course);
        return null;
    }

    @Override
    public Result addCoursePower(DBCoursePower coursePower) {
        return null;
    }

    @Override
    public Result addCoursePlus(DBCoursePlus coursePlus) {
        return null;
    }


    /**
     * 修改某门课程的基本信息
     * @param course
     * @return
     */
    @Override
    public Result updateBaseCourse(DBCourse course) {
        Result result = null;
        if(cm.updateBaseCourse(course)>0)
            result = ResultUtils.success("修改课程基本信息成功");
        else
            result = ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST, "该课程不存在");
        return result;
    }

    /**
     * 修改课程权限
     * @param courseNo
     * @param powers
     * @return
     */
    @Override
    public Result updateCoursePower(int courseNo, List<Integer> powers) {
        if(cm.deleteCoursePower(courseNo)<1)
            return ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST, "该课程不存在");
        for(Integer power: powers)
            cm.addCoursePower(new DBCoursePower(courseNo, power));
        return ResultUtils.success("修改成功");
    }

    /**
     * 修改某期的课程信息
     * @param coursePlus
     * @return
     */
    @Override
    public Result updateCoursePlus(DBCoursePlus coursePlus) {
        Result result = null;
        if(cm.updateCoursePlus(coursePlus)>0)
            result = ResultUtils.success("修改课程信息成功");
        else
            result = ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST, "该课程不存在");
        return result;
    }


    /**
     * 获取全部的课程信息
     * @return
     */
    @Override
    public Result getAllCourseInfo() {
        List<Course> list = cm.getAllCourseInfo();
        return ResultUtils.success("获取课程信息成功", list);
    }

//    /**
//     * 获取某门课程的基本信息
//     * @param courseNo
//     * @return
//     */
//    @Override
//    public Result getCourseInfo(int courseNo) {
//        Result result = null;
//        Course course = cm.getCourseInfo(courseNo);
//        if(course != null)
//            result = ResultUtils.success("获取课程信息成功", course);
//        else
//            result = ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST, "课程信息不存在");
//        return result;
//    }


    /**
     * 根据权限获取可选课程列表
     * @param power
     * @return
     */
    @Override
    public Result getCourseList(int power) {
        List<Course> list = cm.getCourseList(power);
        return ResultUtils.success("获取课程列表成功", list);
    }

    /**
     * 查询某期课程座位选择状况
     * @param courseNo
     * @param stage
     * @return
     */
    @Override
    public Result getSeatStatus(int courseNo, int stage) {
        List<Integer> list = cm.getSeatStatus(courseNo, stage);
        return ResultUtils.success("获取座位列表成功", list);
    }

    /**
     * 学员选课
     * @param stuNo
     * @param courseNo
     * @param stage
     * @param seatNo
     * @return
     */
    @Override
    public Result selectCourse(String stuNo, int courseNo, int stage, int seatNo) {
        Result result = null;
        try {
            cm.selectCourse(stuNo, courseNo, stage, seatNo);
            result = ResultUtils.success("选课成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultUtils.error(ResultUtils.CODE_OPERATE_FAIL, "选课失败，您已经选择该课程或该座位已被选");
        }
        return result;
    }

    /**
     * 取消选课
     * @param courseNo
     * @param stage
     * @param stuNo
     * @return
     */
    @Override
    public Result cancelSelectCourse(int courseNo, int stage, String stuNo) {
        Result result = null;
        Course course = cm.getCourseInfo(courseNo, stage);
        if(course==null) {
            result = ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST,"没有查到课程");
        } else {
            if(course.getStartDateTime().after(new Timestamp(System.currentTimeMillis()))) {
                if (cm.cancelSelectCourse(courseNo, stuNo) > 0)
                    result = ResultUtils.success("取消成功");
                else
                    result = ResultUtils.error(ResultUtils.CODE_FAIL_NO_SELECT, "删除失败，您可能没选该课程");
            } else {
                result = ResultUtils.error(ResultUtils.CODE_OPERATE_FAIL, "该课程已结束，无法取消选课");
            }
        }
        return result;
    }

    /**
     * 管理员强制取消选课
     * @param courseNo
     * @param stuNo
     * @return
     */
    @Override
    public Result cancelSelectCourseByAdmin(int courseNo, String stuNo) {
        Result result = null;
        Course course = cm.getCourseInfo(courseNo, 1);
        if(course==null) {
            result = ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST,"没有查到课程");
        } else {
            if(cm.cancelSelectCourse(courseNo, stuNo)>0)
                result = ResultUtils.success("取消选课成功");
            else
                result = ResultUtils.error(ResultUtils.CODE_FAIL_NO_SELECT, "删除失败，该学员可能未选该课程");
        }

        return result;
    }

    /**
     * 学员获取自己的选课状况
     * @param stuNo
     * @return
     */
    @Override
    public Result getMyCourseSeat(String stuNo) {
        List<CourseSeat> list = cm.getMyCourseSeat(stuNo);
        return ResultUtils.success("获取自己课程信息成功", list);
    }

    /**
     * 管理员查询某节某期课程的选座状况
     * @param courseNo
     * @param stage
     * @return
     */
    @Override
    public Result getSelectCourseInfoByMaster(int courseNo, int stage) {
        List<CourseSeat> list = cm.getSelectCourseInfoByMaster(courseNo, stage);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 查看某个学员已选课程
     * @param stuNo
     * @return
     */
    @Override
    public Result getSelectCourse(String stuNo) {
        List<CourseSeat> list = cm.getSelectCourse(stuNo);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 反馈某期课程到课人员信息（单个人的信息）
     * @param list
     * @param courseNo
     * @return
     */
    @Override
    public Result updateSomeoneCome(List<String> list, int courseNo) {
        for(String stuNo: list)
            cm.updateSomeoneCome(stuNo, courseNo);
        return ResultUtils.success("反馈到课信息成功");
    }

    /**
     * 获取某个学生的已获得学时总额
     * @param stuNo
     * @return
     */
    @Override
    public Result getAcquireTime(String stuNo) {
        Result result = null;
        Integer score = cm.getAcquireTime(stuNo);
        if(score != null)
            result = ResultUtils.success("获取总分成功", score);
        else
            result = ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST, "该学员不存在");
        return result;
    }
}
