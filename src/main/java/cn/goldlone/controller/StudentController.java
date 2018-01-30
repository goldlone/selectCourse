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
     * 用户修改密码
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
     * 获取基层党组织名称
     * @return
     */
    @PostMapping("/getSchools")
    public Result getSchools() {
        return ss.getSchools();
    }

    /**
     * 添加学生
     * @param stu
     * @return
     */
    @PostMapping("/stu/add")
    public Result addStudent(DBStudent stu) {
        return ss.addStudent(stu);
    }

    /**
     * 查询学员权限
     * @param stuNo
     * @return
     */
    @PostMapping("/stu/power")
    public Result getStuPower(String stuNo) {
        return ss.getStuPower(stuNo);
    }

    /**
     * 获取全部学员信息
     * @return
     */
    @PostMapping("/stu/allInfo")
    public Result getAllStuInfo() {
        return ss.getAllStuInfo();
    }

    /**
     *  获取某基层党组织的全部学员信息
     * @param schoolNo
     * @return
     */
    @PostMapping("/stu/schoolInfo")
    public Result getStudentInfoBySchoolNo(int schoolNo) {
        return ss.getStudentInfoBySchoolNo(schoolNo);
    }

    /**
     * 查询某学员的信息
     * @param stuNo
     * @return
     */
    @PostMapping("/stu/infoByNo")
    public Result getStuInfoByNo(String stuNo) {
        return ss.getStuInfoByNo(stuNo);
    }

    /**
     * 管理员修改学员信息
     * @param stu
     * @return
     */
    @PostMapping("/stu/updateInfoByAdmin")
    public Result updateStuInfoAdmin(DBStudent stu) {
        return ss.updateStuInfoAdmin(stu);
    }

    /**
     * 学员自己修改信息
     * @param stu
     * @return
     */
    @PostMapping("/stu/updateInfoBySelf")
    public Result updateStuInfoSelf(DBStudent stu) {
        return ss.updateStuInfoSelf(stu);
    }



}
