package cn.goldlone.service;

import cn.goldlone.Properties;
import cn.goldlone.mapper.StudentMapper;
import cn.goldlone.model.Admin;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
import cn.goldlone.po.*;
import cn.goldlone.utils.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper sm;

    /**
     * 用户登录
     * @param stuNo
     * @param password
     * @return
     */
    public Result login(HttpServletRequest request, String stuNo, String password) {
        LoginInfo info = sm.getStuPassword(stuNo);
        Result result = null;
        if(info==null) {
            result =  ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "会员信息不存在");
        } else {
            if(info.getPassword().equals(DigestUtils.sha256Hex(password))) {
                result = ResultUtil.success(null, "登录成功");
                HttpSession session = request.getSession();
                session.setAttribute(Properties.LOGIN_NO, info.getNo());
                session.setAttribute(Properties.LOGIN_POWER, info.getPower());
                session.setAttribute(Properties.LOGIN_SCHOOL_NO, info.getSchoolNo());
                session.setMaxInactiveInterval(3600);
            }
            else
                result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "登录密码错误");
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
    public Result updatePassword(String stuNo, String password, String newPassword) {
        Result result = null;
        password = DigestUtils.sha256Hex(password);
        newPassword = DigestUtils.sha256Hex(newPassword);
        if(sm.updateStuPassword(stuNo, password, newPassword)>0)
            result = ResultUtil.success("修改密码成功");
        else
            result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "原始密码错误");
        return result;
    }

    /**
     * 获取基层党组织管理员密码
     * @param request
     * @param no
     * @param password
     * @return
     */
    public Result loginAdmin(HttpServletRequest request, String no, String password) {
        LoginInfo info = sm.getAdminPassword(no);
        Result result = null;
        if(info==null) {
            result =  ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "会员信息不存在");
        } else {
            if(info.getPassword().equals(DigestUtils.sha256Hex(password))) {
                result = ResultUtil.success(null, "登录成功");
                HttpSession session = request.getSession();
                session.setAttribute(Properties.LOGIN_NO, info.getNo());
                session.setAttribute(Properties.LOGIN_POWER, info.getPower());
                session.setAttribute(Properties.LOGIN_SCHOOL_NO, info.getSchoolNo());
                session.setMaxInactiveInterval(3600);
            }
            else
                result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "登录密码错误");
        }
        return result;
    }

    /**
     * 修改管理员登录密码
     * @param no
     * @param password
     * @param newPassword
     * @return
     */
    public Result updateAdminPassword(String no, String password, String newPassword) {
        Result result = null;
        password = DigestUtils.sha256Hex(password);
        newPassword = DigestUtils.sha256Hex(newPassword);
        if(sm.updateAdminPassword(no, password, newPassword)>0)
            result = ResultUtil.success("修改密码成功");
        else
            result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "原始密码错误");
        return result;
    }

    /**
     * 获取全部批次信息
     * @return
     */
    public Result getBatch() {
        return ResultUtil.success("获取批次信息成功", sm.getBatch());
    }

    /**
     * 录入管理员信息
     * @param admin
     * @return
     */
    public Result addAdmin(DBAdmin admin) {
        Result result = null;
        admin.setPassword(DigestUtils.sha256Hex(admin.getNo()));
        admin.setPower(1);
        if(sm.addDBAdmin(admin)>0) {
            result = ResultUtil.success("添加管理员成功");
        } else {
            result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "添加管理员失败");
        }
        return result;
    }

    /**
     * 录入管理员信息
     * @param admin
     * @return
     */
    public Result addAdmin(Admin admin) {
        Result result = null;
        admin.setPassword(DigestUtils.sha256Hex(admin.getNo()));
        admin.setPower(1);
        if(sm.addAdmin(admin)>0) {
            result = ResultUtil.success("添加管理员成功");
        } else {
            result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "添加管理员失败");
        }
        return result;
    }

    /**
     * 删除管理员
     * @param no
     * @return
     */
    public Result deleteAdmin(String no) {
        Result result = null;
        if (sm.deleteAdmin(no)>0)
            result = ResultUtil.success("删除成功");
        else
            result = ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "该管理员不存在");
        return result;
    }

    /**
     * 获取全部管理员信息
     * @return
     */
    public Result getAllAdminInfo() {
        List<Admin> list = sm.getAllAdminInfo();
        return ResultUtil.success("获取全部管理员信息成功", list);
    }

    /**
     * 获取所有基层党组织信息
     * @return
     */
    public Result getSchools() {
        List<DBSchool> schools = sm.getSchools();
        schools.remove(0);
        return ResultUtil.success("获取所有基层党组织信息成功", schools);
    }

    /**
     * 获取全部身份信息
     * @return
     */
    public Result getPowers() {
        List<DBPowers> list = sm.getPowers();
        return ResultUtil.success("获取身份信息成功", list);
    }

    /**
     * 单个录入学员基本信息
     * @param stu
     * @return
     */
    public Result addStudent(DBStudent stu) {
        Result result = null;
        System.out.println(stu.toString());
        stu.setPassword(DigestUtils.sha256Hex(stu.getNo()));
        if(sm.addDBStudent(stu)>0) {
            result = ResultUtil.success("添加学员成功");
        } else {
            result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "添加用户失败");
        }
        return result;
    }

    /**
     * 录入学员基本信息
     * @param stu
     * @return
     */
    public Result addStudent(Student stu) {
        Result result = null;
        System.out.println(stu.toString());
        stu.setPassword(DigestUtils.sha256Hex(stu.getNo()));
        if(sm.addStudent(stu)>0) {
            result = ResultUtil.success("添加学员成功");
        } else {
            result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "添加学员失败");
        }
        return result;
    }

    /**
     * 删除学员信息
     * @param no
     * @return
     */
    public Result deleteStudent(String no) {
        Result result = null;
        if (sm.deleteStu(no)>0)
            result = ResultUtil.success("删除成功");
        else
            result = ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "该学员不存在");
        return result;
    }

    /**
     * 获取用户权限
     * @param stuNo
     * @return
     */
    public Result getStuPower(String stuNo) {
        Result result = null;
        Integer power = sm.getStuPower(stuNo);
        if(power != null)
            result = ResultUtil.success("获取权限成功", power);
        else
            result = ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "该用户不存在");
        return result;
    }

    /**
     * 查询学员权限
     * @param request
     * @return
     */
    public Result getStuPower(HttpServletRequest request) {
        Result result = null;
        String stuNo = (String) request.getSession().getAttribute("stuNo");
        Integer power = sm.getStuPower(stuNo);
        if(power != null)
            result = ResultUtil.success("获取权限成功", power);
        else
            result = ResultUtil.error(1, "该用户不存在");
        return result;
    }

    /**
     * 获取全部学生信息
     * @return
     */
    public Result getAllStuInfo() {
        List<Student> list = sm.getAllStuInfo();
        return ResultUtil.success("获取全部学员信息成功", list);
    }

    /**
     * 获取某基层组织的全部学员
     * @param schoolNo
     * @return
     */
    public Result getStudentInfoBySchoolNo(int schoolNo, int batch) {
        List<Student> list = sm.getStudentInfoBySchoolNo(schoolNo, batch);
        return ResultUtil.success("获取学员信息成功", list);
    }

    /**
     * 获取学员信息
     * @param stuNo
     * @return
     */
    public Result getStuInfoByNo(String stuNo) {
        Student stu = sm.getStuInfo(stuNo);
        return ResultUtil.success("获取学员信息成功", stu);
    }

    /**
     * 根据部分姓名查询学员信息
     * @param name
     * @return
     */
    public Result getStuInfoByName(String name) {
        List<Student> list = sm.getStuInfoByName(name);
       return ResultUtil.success("查询成功", list);
    }

    /**
     * 获取管理员信息
     * @param no
     * @return
     */
    public Result getAdminInfoByNo(String no) {
        Admin admin = sm.getAdminInfo(no);
        return ResultUtil.success("获取管理员信息成功", admin);
    }

    /**
     * 管理员修改某个学生的基本信息
     * @param stu
     * @return
     */
    public Result updateStuInfoAdmin(DBStudent stu) {
        Result result = null;
        if(sm.updateStuInfoAdmin(stu)>0)
            result = ResultUtil.success("修改学生信息成功");
        else
            result = ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "该学员不存在");
        return result;
    }

    /**
     * 自己修改基本信息
     * @param stu
     * @return
     */
    public Result updateStuInfoSelf(DBStudent stu) {
        Result result = null;
        if(sm.updateStuInfoSelf(stu)>0)
            result = ResultUtil.success("修改成功");
        else
            result = ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "学生信息不存在");
        return result;
    }

    /**
     * 一批学员结束党课
     * @return
     */
    public Result endCourse() {
        int num = sm.endCourse();
        return ResultUtil.success("设置该批次学生结束成功", num);
    }

    /**
     * 获取反馈信息
     * @return
     */
    public Result getFeedback() {
        List<DBFeedback> list = sm.getFeedback();
        return ResultUtil.success("获取反馈信息成功", list);
    }

    /**
     * 提交反馈信息
     * @return
     */
    public Result feedback(String stuNo, String content) {
        sm.feedback(stuNo, content);
        return ResultUtil.success("提交反馈成功");
    }

    /**
     * 处理反馈信息
     * @param id
     * @param no
     * @return
     */
    public Result dealFeedback(long id, String no) {
        if(sm.dealFeedback(id, no)==0)
            return ResultUtil.error(ResultUtil.CODE_RESULT_NOT_EXIST, "该条信息不存在");
        return ResultUtil.success("处理成功");
    }

}