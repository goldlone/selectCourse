package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.service.CourseService;
import cn.goldlone.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by CN on 2018/1/28.
 */
@RestController
public class CourseController extends BaseController {

    @Autowired
    private CourseService cs;

    /**
     * 发布课程
     * @return
     */
    @PostMapping("/course/public")
    public Result publicCourse(){
        return ResultUtils.success("该功能正在实现");
    }

    /**
     * 获取全部的课程信息
     * @return
     */
    @PostMapping("/course/allInfo")
    public Result getAllCourseInfo(){
        return cs.getAllCourseInfo();
    }

    /**
     * 根据权限获取可选课程列表
     * @param power
     * @return
     */
    @PostMapping("/course/list")
    public Result getCourseList(int power) {
        return cs.getCourseList(power);
    }

    /**
     * 查询某期课程座位选择状况
     * @param courseNo
     * @param stage
     * @return
     */
    @PostMapping("/course/seatStatus")
    public Result getSeatStatus(int courseNo, int stage) {
        return cs.getSeatStatus(courseNo, stage);
    }

    /**
     * 学员选课选座
     * @param courseNo
     * @param stage
     * @param seatNo
     * @return
     */
    @PostMapping("/course/select")
    public Result selectCourse(HttpServletRequest request, int courseNo, int stage, int seatNo) {
        String stuNo = (String) request.getSession().getAttribute("stuNo");
        return cs.selectCourse(stuNo, courseNo, stage, seatNo);
    }

    /**
     * 取消选课
     * @param request
     * @param courseNo
     * @return
     */
    @PostMapping("/course/cancel")
    public Result cancelSelectCourse(HttpServletRequest request, int courseNo) {
        String stuNo = (String) request.getSession().getAttribute("stuNo");
        return cs.cancelSelectCourse(courseNo, stuNo);
    }

    /**
     * 管理员查询某节某期课程的选座状况
     * @param courseNo
     * @param stage
     * @return
     */
    @PostMapping("/course/selectStatus")
    public Result getSelectCourseInfoByMaster(int courseNo, int stage) {
        return cs.getSelectCourseInfoByMaster(courseNo, stage);
    }

    /**
     * 查看某个学员已选课程
     * @param stuNo
     * @return
     */
    @PostMapping("/course/stuSelectStatus")
    public Result getSelectCourse(String stuNo) {
        return cs.getSelectCourse(stuNo);
    }

    /**
     * 反馈某期课程到课人员信息
     * @param stu
     * @param courseNo
     * @return
     */
    @PostMapping("/course/feedback")
    public Result updateSomeoneCome(List<String> stu, int courseNo) {
        return cs.updateSomeoneCome(stu, courseNo);
    }

    /**
     * 获取某个学生的已获得学时总额
     * @param stuNo
     * @return
     */
    @PostMapping("/course/stuAllTime")
    public Result getAcquireTime(String stuNo) {
        return cs.getAcquireTime(stuNo);
    }

}
