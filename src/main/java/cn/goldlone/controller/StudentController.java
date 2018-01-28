package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.po.DBStudent;
import cn.goldlone.service.StudentService;
import cn.goldlone.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CN on 2018/1/26.
 */
@RestController
public class StudentController extends BaseController {

    @Autowired
    private StudentService ss;

    /**
     * 登录
     * @param request
     * @param stuNo
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request, String stuNo, String password) {
        return ss.login(request, stuNo, password);
    }

    /**
     *
     * @param stuNo
     * @param password
     * @param newPassword
     * @return
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(String stuNo, String password, String newPassword) {
        return ss.updatePassword(stuNo, password, newPassword);
    }

    /**
     *
     * @return
     */
    @PostMapping("/getSchools")
    public Result getSchools() {
        return ss.getSchools();
    }

    @PostMapping("/stu/add")
    public Result addStudent(DBStudent stu) {
        return ss.addStudent(stu);
    }

    @PostMapping("/stu/power")
    public Result getStuPower(String stuNo) {
        return ss.getStuPower(stuNo);
    }

    @PostMapping("/stu/getAllInfo")
    public Result getAllStuInfo() {
        return ss.getAllStuInfo();
    }

    @PostMapping("/stu/getInfoByNo")
    public Result getStuInfoByNo(String stuNo) {
        return ss.getStuInfoByNo(stuNo);
    }

    @PostMapping("/stu/updateInfoByAdmin")
    public Result updateStuInfoAdmin(DBStudent stu) {
        return ss.updateStuInfoAdmin(stu);
    }

    @PostMapping("/stu/updateInfoBySelf")
    public Result updateStuInfoSelf(DBStudent stu) {
        return ss.updateStuInfoSelf(stu);
    }



}
