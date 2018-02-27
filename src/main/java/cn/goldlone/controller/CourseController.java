package cn.goldlone.controller;

import cn.goldlone.Properties;
import cn.goldlone.model.Course;
import cn.goldlone.model.CourseSeat;
import cn.goldlone.model.Result;
import cn.goldlone.po.DBCourse;
import cn.goldlone.po.DBCoursePlus;
import cn.goldlone.po.DBCoursePower;
import cn.goldlone.service.CourseService;
import cn.goldlone.utils.ExcelUtil;
import cn.goldlone.utils.IOUtil;
import cn.goldlone.utils.ResultUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
    public Result publicCourse(HttpServletRequest request) throws IOException {
        String rec = IOUtil.streamToString(request.getInputStream());
        JSONObject recJSON = new JSONObject(rec);
        String name = recJSON.getString("name");
        Integer time = recJSON.getInt("time");
        List powers = recJSON.getJSONArray("powers").toList();
        List<DBCoursePlus> plus = new ArrayList<>();
        JSONArray arr = recJSON.getJSONArray("plus");
        for(Object obj: arr) {
            JSONObject json = (JSONObject) obj;
            Integer stage = json.getInt("stage");
            String classroom = json.getString("classroom");
            String teacher = json.getString("teacher");
            Timestamp startDateTime = Timestamp.valueOf(json.getString("startDateTime"));
            Timestamp endDateTime = Timestamp.valueOf(json.getString("endDateTime"));
            plus.add(new DBCoursePlus(stage, classroom, teacher, startDateTime, endDateTime));
        }

        List<DBCoursePower> powers1 = new ArrayList<>();
        for(Object power: powers)
            powers1.add(new DBCoursePower((Integer) power));

        return cs.publicCourse(new DBCourse(name, time), powers1, plus);
    }


    /**
     * 获取修改课程的信息
     * @param courseNo
     * @return
     */
    @PostMapping("/course/infoOfUpdate")
    public String getUpdateCourseInfo(int courseNo, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Result res =  cs.getUpdateCourseInfo(courseNo);
        JSONObject json = new JSONObject();
        json.put("code", res.getCode());
        json.put("msg", res.getMsg());
        json.put("data", res.getData());
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 修改课程信息
     * @param request
     * @return
     */
    @PostMapping("/course/update")
    public Result updateCourse(HttpServletRequest request) throws IOException {
        String rec = IOUtil.streamToString(request.getInputStream());
        JSONObject recJSON = new JSONObject(rec);
        int courseNo = recJSON.getInt("courseNo");
        String name = recJSON.getString("name");
        Integer time = recJSON.getInt("time");
        List powers = recJSON.getJSONArray("powers").toList();
        List<DBCoursePlus> plus = new ArrayList<>();
        JSONArray arr = recJSON.getJSONArray("plus");
        for(Object obj: arr) {
            JSONObject json = (JSONObject) obj;
            Integer stage = json.getInt("stage");
            String classroom = json.getString("classroom");
            String teacher = json.getString("teacher");
            Timestamp startDateTime = Timestamp.valueOf(json.getString("startDateTime"));
            Timestamp endDateTime = Timestamp.valueOf(json.getString("endDateTime"));
            plus.add(new DBCoursePlus(courseNo, stage, classroom, teacher, startDateTime, endDateTime));
        }

        DBCourse course1 = new DBCourse(name, time);
        course1.setNo(courseNo);
        return cs.updateCourse(course1, powers, plus);
    }


    /**
     * 删除课程
     * @param courseNo
     * @return
     */
    @PostMapping("/course/delete")
    public Result deleteCourse(int courseNo) {
        return cs.deleteCourse(courseNo);
    }

    /**
     * 获取全部的课程信息
     * @return
     */
    @PostMapping("/course/allInfo")
    public Result getAllCourseInfo() {
        return cs.getAllCourseInfo();
    }

    /**
     * 根据权限获取可选课程列表
     * @return
     */
    @PostMapping("/course/list")
    public Result getCourseList(HttpServletRequest request) {
        int power = (int) request.getSession().getAttribute(Properties.LOGIN_POWER);
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
        String stuNo = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
//        String stuNo = "123";
        return cs.selectCourse(stuNo, courseNo, stage, seatNo);
    }

    /**
     * 学员获取自己的选课状况
     * @param request
     * @return
     */
    @PostMapping("/course/myCourse")
    public Result getMyCourseSeat(HttpServletRequest request) {
//        String stuNo = "123";
        String stuNo = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
        return cs.getMyCourseSeat(stuNo);
    }

    /**
     * 取消选课
     * @param request
     * @param courseNo
     * @return
     */
    @PostMapping("/course/cancel")
    public Result cancelSelectCourse(HttpServletRequest request, int courseNo, int stage) {
        String stuNo = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
//        String stuNo = "123";
        return cs.cancelSelectCourse(courseNo, stage, stuNo);
    }


    /**
     * 管理员强制取消选课
     * @param stuNo
     * @param courseNo
     * @return
     */
    @PostMapping("/course/admin/cancel")
    public Result cancelSelectCourseByAdmin(String stuNo, int courseNo) {
        return cs.cancelSelectCourseByAdmin(courseNo, stuNo);
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
     * 导出某课程某期的选座状况
     * @param courseNo
     * @param stage
     * @param response
     * @return
     */
    @GetMapping("/course/exportSelectStatus")
    public Result exportSelectSeatStatus(int courseNo, int stage, HttpServletResponse response) {
        Result res = cs.getSelectCourseInfoByMaster(courseNo, stage);
        List<CourseSeat> list = (List<CourseSeat>) res.getData();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "selectSeatStatus.xls");
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        Result result = null;
        try {
            os = response.getOutputStream();
            File file = ExcelUtil.exportSelectSeatStatus(list);
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
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
     * 获取已结束的课程信息
     * @return
     */
    @PostMapping("/course/end")
    public Result getCanFeedbackCourse() {
        return cs.getCanFeedbackCourse();
    }


    /**
     * 反馈某期课程到课人员信息
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/course/feedback")
    public Result updateSomeoneCome(HttpServletRequest request) throws IOException {
        JSONObject recJSON = new JSONObject(IOUtil.streamToString(request.getInputStream()));
        System.out.println(recJSON);
        List list = recJSON.getJSONArray("stu").toList();
        return cs.updateSomeoneCome(list, recJSON.getInt("courseNo"));
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
