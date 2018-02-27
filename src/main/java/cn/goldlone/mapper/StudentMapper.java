package cn.goldlone.mapper;

import cn.goldlone.model.Admin;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Student;
import cn.goldlone.po.DBAdmin;
import cn.goldlone.po.DBPowers;
import cn.goldlone.po.DBSchool;
import cn.goldlone.po.DBStudent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * Created by CN on 2017/12/17.
 */
@Mapper
public interface StudentMapper {
    /**
     * 获取学员密码
     * @param no
     * @return
     */
    @Select("SELECT no, password, schoolNo, power " +
            "FROM Student " +
            "WHERE no = #{no};")
    public LoginInfo getStuPassword(String no);

    /**
     * 获取基层党组织管理员密码
     * @param no
     * @return
     */
    @Select("SELECT no, password, schoolNo, power " +
            "FROM Admin " +
            "WHERE no=#{no};")
    public LoginInfo getAdminPassword(String no);

    /**
     * 修改学员登录密码
     * @param stuNo
     * @param password
     * @return
     */
    @Update("UPDATE Student " +
            "SET password=#{newPassword} " +
            "WHERE no=#{stuNo} AND " +
            "   password=#{password};")
    public Integer updateStuPassword(@Param("stuNo")String stuNo,
                                     @Param("password")String password,
                                     @Param("newPassword")String newPassword);

    /**
     * 修改管理员登录密码
     * @param no
     * @param password
     * @param newPassword
     * @return
     */
    @Update("UPDATE Admin " +
            "SET password=#{newPassword} " +
            "WHERE no=#{no} AND " +
            "   password=#{password};")
    public Integer updateAdminPassword(@Param("no")String no,
                                       @Param("password")String password,
                                       @Param("newPassword")String newPassword);

    /**
     * 获取全部身份权限
     * @return
     */
    @Select("SELECT * FROM Powers;")
    public List<DBPowers> getPowers();

    /**
     * 获取所有基层党组织信息
     * @return
     */
    @Select("SELECT * FROM Schools;")
    public List<DBSchool> getSchools();

    /**
     * 根据基层党委名查询编号
     * @param name
     * @return
     */
    @Select("SELECT no FROM Schools WHERE name=#{name};")
    public Integer getSchoolNoByName(String name);


    /**
     * 录入学员基本信息
     * @param stu
     * @return
     */
    @Insert("INSERT " +
            "INTO Student(name, no, schoolNo, gender, " +
            "   nation, birth, type, grade, position, " +
            "   applyDate, beActivistDate, " +
            "   beDevelopDate, power, password) " +
            "VALUES (#{name}, #{no}, #{schoolNo},  #{gender}, " +
            "   #{nation}, #{birth}, #{type}, #{grade}, #{position}, " +
            "   #{applyDate}, #{beActivistDate}, " +
            "   #{beDevelopDate}, #{power}, #{password});")
    public Integer addDBStudent(DBStudent stu);

    /**
     * 录入学员信息
     * @param stu
     * @return
     */
    @Insert("INSERT " +
            "INTO Student(name, no, schoolNo, gender, " +
            "   nation, birth, type, grade, position, " +
            "   applyDate, beActivistDate, " +
            "   beDevelopDate, power, password) " +
            "VALUES(#{name}, #{no}, (SELECT no FROM Schools WHERE Schools.name=#{school}), " +
            "   #{gender}, #{nation}, #{birth}, #{type}, #{grade}, " +
            "   #{position}, #{applyDate}, #{beActivistDate}, #{beDevelopDate}, " +
            "   (SELECT no FROM Powers WHERE identity=#{identity}), #{password});")
    public Integer addStudent(Student stu);

    /**
     * 删除学员
     * @param no
     * @return
     */
    @Delete("DELETE FROM Student WHERE no=#{no};")
    public Integer deleteStu(String no);

    /**
     * 录入管理员信息
     * @param admin
     * @return
     */
    @Insert("INSERT " +
            "INTO Admin(no, name, schoolNo, password, power) " +
            "VALUES(#{no}, #{name}, " +
            "   (SELECT no FROM Schools WHERE Schools.name=#{school}), " +
            "   #{password}, #{power});")
    public Integer addAdmin(Admin admin);

    /**
     * 录入管理员信息
     * @param admin
     * @return
     */
    @Insert("INSERT " +
            "INTO Admin(no, name, schoolNo, password, power) " +
            "VALUES(#{no}, #{name}, #{schoolNo}, #{password}, #{power});")
    public Integer addDBAdmin(DBAdmin admin);

    /**
     * 删除管理员
     * @param no
     * @return
     */
    @Delete("DELETE FROM Admin WHERE no=#{no};")
    public Integer deleteAdmin(String no);

    /**
     * 获取全部管理员信息
     * @return
     */
    @Select("SELECT Admin.no, Admin.name, Schools.name school, power " +
            "FROM Admin, Schools " +
            "WHERE schoolNo=Schools.no;")
    public List<Admin> getAllAdminInfo();

    /**
     * 获取某个管理员的信息
     * @param no
     * @return
     */
    @Select("SELECT Admin.no, Admin.name, Schools.name school, power " +
            "FROM Admin, Schools " +
            "WHERE Admin.no=#{no} AND schoolNo=Schools.no;")
    public Admin getAdminInfo(String no);

    /**
     * 获取学员权限
     * @param stuNo
     * @return
     */
    @Select("SELECT power " +
            "FROM Student " +
            "WHERE no = #{stuNo};")
    public Integer getStuPower(String stuNo);

    /**
     * 获取全部学生信息
     * @return
     */
    @Select("SELECT s1.no, s1.name, s2.name school, " +
            "   s1.gender, s1.nation, s1.birth, s1.type, " +
            "   s1.grade, s1.position, s1.applyDate, " +
            "   s1.beActivistDate, s1.beDevelopDate, " +
            "   s1.power, p.identity " +
            "FROM Student s1, Powers p, Schools s2 " +
            "WHERE s1.power=p.no AND " +
            "   s1.schoolNo=s2.no;")
    public List<Student> getAllStuInfo();

    /**
     * 根据学院编号获取学生信息
     * @param schoolNo
     * @return
     */
    @Select("SELECT s1.no, s1.name, s2.name school, " +
            "   s1.gender, s1.nation, s1.birth, s1.type, " +
            "   s1.grade, s1.position, s1.applyDate, " +
            "   s1.beActivistDate, s1.beDevelopDate, " +
            "   s1.power, p.identity " +
            "FROM Student s1, Powers p, Schools s2 " +
            "WHERE s2.no=#{schoolNo} AND " +
            "   s1.power=p.no AND " +
            "   s1.schoolNo=s2.no;")
    public List<Student> getStudentInfoBySchoolNo(int schoolNo);


    /**
     * 根据学号查询学生信息
     * @param stuNo
     * @return
     */
    @Select("SELECT s1.no, s1.name, s2.name school, " +
            "   s1.gender, s1.nation, s1.birth, s1.type, " +
            "   s1.grade, s1.position, s1.applyDate, " +
            "   s1.beActivistDate, s1.beDevelopDate, " +
            "   s1.power, p.identity " +
            "FROM Student s1, Powers p, Schools s2 " +
            "WHERE s1.no=#{stuNo} AND " +
            "   s1.power=p.no AND " +
            "   s1.schoolNo=s2.no;")
    public Student getStuInfo(String stuNo);

    /**
     * 根据部分姓名查询学员信息
     * @param name
     * @return
     */
    @Select("SELECT s1.no, s1.name, s2.name school, " +
            "   s1.gender, s1.nation, s1.birth, s1.type, " +
            "   s1.grade, s1.position, s1.applyDate, " +
            "   s1.beActivistDate, s1.beDevelopDate, " +
            "   s1.power, p.identity " +
            "FROM Student s1, Powers p, Schools s2 " +
            "WHERE s1.name LIKE concat('%',#{name},'%') AND " +
            "   s1.power=p.no AND " +
            "   s1.schoolNo=s2.no;")
    public List<Student> getStuInfoByName(String name);


    /**
     * 管理员修改某个学生的基本信息
     * @param stu
     * @return
     */
    @Update("UPDATE Student " +
            "SET name=#{name}, " +
            "   schoolNo=#{schoolNo}, " +
            "   gender=#{gender}, " +
            "   nation=#{nation}, " +
            "   birth=#{birth}, " +
            "   type=#{type}, " +
            "   grade=#{grade}, " +
            "   position=#{position}, " +
            "   applyDate=#{applyDate}, " +
            "   beActivistDate=#{beActivistDate}, " +
            "   beDevelopDate=#{beDevelopDate}, " +
            "   power=#{power} " +
            "WHERE no = #{no};")
    public Integer updateStuInfoAdmin(DBStudent stu);


    /**
     * 自己修改用户信息
     * @param stu
     * @return
     */
    @Update("UPDATE Student " +
            "SET name=#{name}, " +
            "   gender=#{gender}, " +
            "   nation=#{nation}, " +
            "   birth=#{birth}, " +
            "   type=#{type}, " +
            "   grade=#{grade}, " +
            "   position=#{position} " +
            "WHERE no=#{no};")
    public Integer updateStuInfoSelf(DBStudent stu);
}
