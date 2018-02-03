package cn.goldlone.service.impl;

import cn.goldlone.Properties;
import cn.goldlone.mapper.StudentMapper;
import cn.goldlone.model.Admin;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
import cn.goldlone.po.DBAdmin;
import cn.goldlone.po.DBPowers;
import cn.goldlone.po.DBSchool;
import cn.goldlone.po.DBStudent;
import cn.goldlone.service.StudentService;
import cn.goldlone.utils.ResultUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper sm;

    /**
     * 用户登录
     * @param stuNo
     * @param password
     * @return
     */
    @Override
    public Result login(HttpServletRequest request, String stuNo, String password) {
        LoginInfo info = sm.getStuPassword(stuNo);
        Result result = null;
        if(info==null) {
            result =  ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST, "会员信息不存在");
        } else {
            if(info.getPassword().equals(DigestUtils.sha256Hex(password))) {
                result = ResultUtils.success(null, "登录成功");
                request.getSession(true).setAttribute(Properties.LOGIN_NO, info.getNo());
                request.getSession(true).setAttribute(Properties.LOGIN_POWER, info.getPower());
                request.getSession(true).setAttribute(Properties.LOGIN_SCHOOL_NO, info.getSchoolNo());
                request.getSession(true).setMaxInactiveInterval(120);
            }
            else
                result = ResultUtils.error(ResultUtils.CODE_OPERATE_FAIL, "登录密码错误");
        }
        return result;
    }

    /**
     * 修改登录密码
     * @param stuNo
     * @param password
     * @param newPassword
     * @return
     */
    @Override
    public Result updatePassword(String stuNo, String password, String newPassword) {
        Result result = null;
        password = DigestUtils.sha256Hex(password);
        newPassword = DigestUtils.sha256Hex(newPassword);
        if(sm.updateStuPassword(stuNo, password, newPassword)>0)
            result = ResultUtils.success("修改密码成功");
        else
            result = ResultUtils.error(ResultUtils.CODE_OPERATE_FAIL, "原始密码错误");
        return result;
    }

    /**
     * 获取基层党组织管理员密码
     * @param request
     * @param no
     * @param password
     * @return
     */
    @Override
    public Result loginAdmin(HttpServletRequest request, String no, String password) {
        LoginInfo info = sm.getAdminPassword(no);
        Result result = null;
        if(info==null) {
            result =  ResultUtils.error(ResultUtils.CODE_RESULT_NOT_EXIST, "会员信息不存在");
        } else {
            if(info.getPassword().equals(DigestUtils.sha256Hex(password))) {
                result = ResultUtils.success(null, "登录成功");
                request.getSession(true).setAttribute(Properties.LOGIN_NO, info.getNo());
                request.getSession(true).setAttribute(Properties.LOGIN_POWER, info.getPower());
                request.getSession(true).setAttribute(Properties.LOGIN_SCHOOL_NO, info.getSchoolNo());
                request.getSession(true).setMaxInactiveInterval(120);
            }
            else
                result = ResultUtils.error(ResultUtils.CODE_OPERATE_FAIL, "登录密码错误");
        }
        return result;
    }

    @Override
    public Result updateAdminPassword(String no, String password, String newPassword) {
        return null;
    }

    @Override
    public Result addAdmin(DBAdmin admin) {
        return null;
    }

    @Override
    public Result addAdmin(Admin admin) {
        return null;
    }

    @Override
    public Result getAllAdminInfo() {
        return null;
    }

    /**
     * 获取所有基层党组织信息
     * @return
     */
    @Override
    public Result getSchools() {
        List<DBSchool> schools = sm.getSchools();
        return ResultUtils.success("获取所有基层党组织信息成功", schools);
    }

    /**
     * 获取全部身份信息
     * @return
     */
    @Override
    public Result getPowers() {
        List<DBPowers> list = sm.getPowers();
        return ResultUtils.success("获取身份信息成功", list);
    }

    /**
     * 录入学员基本信息
     * @param stu
     * @return
     */
    @Override
    public Result addStudent(DBStudent stu) {
        Result result = null;
        System.out.println(stu.toString());
        stu.setPassword(DigestUtils.sha256Hex(stu.getPassword()));
        if(sm.addDBStudent(stu)>0) {
            result = ResultUtils.success("添加学员成功");
        } else {
            result = ResultUtils.error(1, "添加用户失败");
        }
        return result;
    }

    @Override
    public Result addStudent(Student stu) {
        sm.addStudent(stu);
        return null;
    }

    /**
     * 获取用户权限
     * @param stuNo
     * @return
     */
    @Override
    public Result getStuPower(String stuNo) {
        Result result = null;
        Integer power = sm.getStuPower(stuNo);
        if(power != null)
            result = ResultUtils.success("获取权限成功", power);
        else
            result = ResultUtils.error(1, "该用户不存在");
        return result;
    }

    public Result getStuPower(HttpServletRequest request) {
        Result result = null;
        String stuNo = (String) request.getSession().getAttribute("stuNo");
        Integer power = sm.getStuPower(stuNo);
        if(power != null)
            result = ResultUtils.success("获取权限成功", power);
        else
            result = ResultUtils.error(1, "该用户不存在");
        return result;
    }

    /**
     * 获取全部学生信息
     * @return
     */
    @Override
    public Result getAllStuInfo() {
        List<Student> list = sm.getAllStuInfo();
        return ResultUtils.success("获取全部学员信息成功", list);
    }

    /**
     * 获取某基层组织的全部学员
     * @param schoolNo
     * @return
     */
    @Override
    public Result getStudentInfoBySchoolNo(int schoolNo) {
        List<Student> list = sm.getStudentInfoBySchoolNo(schoolNo);
        return ResultUtils.success("获取学员信息成功", list);
    }

    /**
     * 获取学员信息
     * @param stuNo
     * @return
     */
    @Override
    public Result getStuInfoByNo(String stuNo) {
        Student stu = sm.getStuInfo(stuNo);
        return ResultUtils.success("获取学员信息成功", stu);
    }

    /**
     * 管理员修改某个学生的基本信息
     * @param stu
     * @return
     */
    @Override
    public Result updateStuInfoAdmin(DBStudent stu) {
        Result result = null;
        if(sm.updateStuInfoAdmin(stu)>0)
            result = ResultUtils.success("修改学生信息成功");
        else
            result = ResultUtils.error(1, "该学员不存在");
        return result;
    }

    /**
     * 自己修改基本信息
     * @param stu
     * @return
     */
    @Override
    public Result updateStuInfoSelf(DBStudent stu) {
        Result result = null;
        if(sm.updateStuInfoSelf(stu)>0)
            result = ResultUtils.success("修改成功");
        else
            result = ResultUtils.error(1, "学生信息不存在");
        return result;
    }

}