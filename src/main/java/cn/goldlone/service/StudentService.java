package cn.goldlone.service;

import java.io.IOException;

/**
 * Created by CN on 2017/12/18.
 */
public interface StudentService {
    // 用户登录
    public String signin() throws IOException;
    // 注销登录
    public String logout() throws IOException;
    // 修改密码
    public String updatePassword() throws IOException;
    // 获取单个学生基本信息
    public String getStudentInfo() throws IOException;
    // 获取全部学生信息
    public String getAllStudentInfo() throws IOException;
    // 修改学生基本信息
    public String updateStudentInfo() throws IOException;
    // 管理员修改学员信息（管理员）
    public String updateStudentInfoByMaster() throws IOException;

}
