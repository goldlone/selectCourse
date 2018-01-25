package cn.goldlone.po;

import cn.goldlone.model.Student;

/**
 * Created by CN on 2017/12/17.
 */
public class DBStudent {
    // 学号
    private String no;
    // 姓名
    private String name;
    // 年级
    private int grade;
    // 学院
    private int schoolNo;
    // 专业
    private String major;
    // 年龄
    private int age;
    // 性别
    private String gender;
    // 权限
    private int power;
    // 密码
    private String password;

    public DBStudent(){
        this.power = -1;
    }

    public DBStudent(Student stu) {
        this.no = stu.getNo();
        this.name = stu.getName();
        this.grade = stu.getGrade();
//        this.schoolNo = stu.getSchool();
        this.major = stu.getMajor();
        this.age = stu.getAge();
        this.gender = stu.getGender();
        this.power = stu.getPower();
        this.password = this.getPassword();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(int schoolNo) {
        this.schoolNo = schoolNo;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DBStudent{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", schoolNo='" + schoolNo + '\'' +
                ", major='" + major + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", power=" + power +
                ", password='" + password + '\'' +
                '}';
    }
}
