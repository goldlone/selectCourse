package cn.goldlone.service;

import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
import cn.goldlone.po.DBPowers;
import cn.goldlone.po.DBStudent;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by CN on 2017/12/18.
 */
public interface StudentService {

    public Result login(HttpServletRequest request, String stuNo, String password);
    public Result updatePassword(String stuNo, String password, String newPassword);
    public Result getSchools();
    public Result getPowers();
    public Result addStudent(DBStudent stu);
    public Result addStudent(Student stu);
    public Result getStuPower(String stuNo);
    public Result getAllStuInfo();
    public Result getStudentInfoBySchoolNo(int schoolNo);
    public Result getStuInfoByNo(String stuNo);
    public Result updateStuInfoAdmin(DBStudent stu);
    public Result updateStuInfoSelf(DBStudent stu);
}
