package cn.goldlone.mapper;

import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Student;
import cn.goldlone.po.DBStudent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 
 * Created by CN on 2017/12/17.
 */
@Mapper
public interface StudentMapper {
    /**
     * 获取用户密码
     * @param stuNo
     * @return
     */
    @Results({
            @Result(property = "stuNo", column = "no"),
            @Result(property = "password", column = "password"),
            @Result(property = "power", column = "power"),
    })
    @Select("SELECT no, password, power " +
            "FROM Student " +
            "WHERE no = #{stuNo};")
    public LoginInfo getPassword(String stuNo);


    /**
     * 修改登录密码
     * @param stuNo
     * @param password
     * @return
     */
    @Update("UPDATE Student " +
            "SET password=#{password} " +
            "WHERE no=#{stuNo};")
    public Integer updatePassword(@Param("stuNo")String stuNo,
                                  @Param("password")String password);


    /**
     * 录入用户基本信息
     * @param stu
     * @return
     */
    @Insert("INSERT " +
            "INTO student (no, name, grade, schoolNo, major," +
            "   age, gender, power, password) " +
            "VALUES (#{no}, #{name}, #{grade}, #{schoolNo}, #{major}," +
            "   #{age}, #{gender}, #{power}, #{password}); ")
    public Integer addStudent(DBStudent stu);


    /**
     * 获取用户权限
     * @param stuNo
     * @return
     */
    @Select("SELECT power " +
            "FROM student " +
            "WHERE no = #{stuNo};")
    public Integer getStuPower(String stuNo);


    /**
     *  获取全部学生信息
     * @return
     */
    @Select("SELECT Student.no,Student.name,grade,Schools.name school,major,age,gender,power,identity " +
            "FROM Powers,Student,Schools " +
            "WHERE Student.power=Powers.no AND Student.schoolNo=Schools.no;")
    public List<Student> getAllStuInfo();


    /**
     * 根据学院名获取学生信息
     * @param school
     * @return
     */
    @Select("SELECT Student.no, Student.name, grade," +
            "   Schools.name school, major, age," +
            "   gender, power, identity " +
            "FROM Powers,Student,Schools " +
            "WHERE Schools.name=#{school} AND " +
            "   Student.schoolNo=Schools.no " +
            "   AND Student.power=Powers.no; ")
    public List<Student> getStudentInfoBySchool(String school);


    /**
     * 根据学院编号获取学生信息
     * @param schoolNo
     * @return
     */
    @Select("SELECT Student.no, Student.name, grade, " +
            "   Schools.name school, major, age, " +
            "   gender, power, identity " +
            "FROM Powers,Student,Schools " +
            "WHERE Student.schoolNo=17 AND " +
            "   Student.schoolNo=Schools.no AND " +
            "   Student.power=Powers.no;")
    public List<Student> getStudentInfoBySchoolNo(int schoolNo);


    /**
     * 根据学号查询学生信息
     * @param stuNo
     * @return
     */
    @Select("SELECT Student.no, Student.name, grade, " +
            "Schools.name school, major, age, " +
            "gender, power, identity " +
            "FROM Powers,Student,Schools " +
            "WHERE Student.no=#{stuNo} AND " +
            "Student.schoolNo=Schools.no AND " +
            "Student.power=Powers.no;")
    public Student getStuInfo(String stuNo);


    /**
     * 管理员修改某个学生的基本信息
     * @param stu
     * @return
     */
    @Update("UPDATE Student " +
            "SET name=#{name}," +
            "   grade=#{grade}," +
            "   schoolNo=#{schoolNo}," +
            "   major=#{major}," +
            "   age=#{age}," +
            "   gender=#{gender}," +
            "   power=#{power} " +
            "WHERE no = #{no}")
    public Integer updateStuInfoAdmin(DBStudent stu);


    /**
     * 自己修改用户信息
     * @param stu
     * @return
     */
    @Update("UPDATE Student " +
            "SET name=#{name}," +
            "   grade=#{grade}," +
            "   schoolNo=#{schoolNo}," +
            "   major=#{major}," +
            "   age=#{age}," +
            "   gender=#{gender}," +
            "WHERE no = #{no}")
    public Integer updateStuInfoSelf(DBStudent stu);
}
