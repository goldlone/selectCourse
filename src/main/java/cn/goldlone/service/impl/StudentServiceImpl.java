package cn.goldlone.service.impl;

import cn.goldlone.mapper.StudentMapper;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
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
//        System.out.println(stuNo+"/"+password);
//        if(sm==null) {
//            System.out.println("未创建");
//            return new Result();
//        }
        LoginInfo info = sm.getPassword(stuNo);
        Result result = null;
        if(info==null) {
            result =  ResultUtils.error(1, "会员信息不存在");
        } else {
            if(info.getPassword().equals(DigestUtils.sha256Hex(password))) {
                result = ResultUtils.success(null, "登录成功");
                request.getSession(true).setAttribute("stuNo", info.getStuNo());
                request.getSession(true).setAttribute("power", info.getPower());
                request.getSession(true).setMaxInactiveInterval(30);
            }
            else
                result = ResultUtils.error(2, "登录密码错误");
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
        if(sm.updatePassword(stuNo, password, newPassword)>0)
            result = ResultUtils.success("修改密码成功");
        else
            result = ResultUtils.error(1, "原始密码错误");
        return result;
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
     * 录入学员基本信息
     * @param stu
     * @return
     */
    @Override
    public Result addStudent(DBStudent stu) {
        Result result = null;
        stu.setPassword(DigestUtils.sha256Hex(stu.getPassword()));
        if(sm.addStudent(stu)>0) {
            result = ResultUtils.success("添加学员成功");
        } else {
            result = ResultUtils.error(1, "添加用户失败");
        }
        return result;
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
        if(sm.updateStuInfoAdmin(stu)>0)
            result = ResultUtils.success("修改成功");
        else
            result = ResultUtils.error(1, "学生信息不存在");
        return result;
    }

}