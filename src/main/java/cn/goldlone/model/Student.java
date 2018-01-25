package cn.goldlone.model;

/**
 * Created by CN on 2017/12/18.
 */
public class Student {
    // 学号
    private String no;
    // 姓名
    private String name;
    // 年级
    private int grade;
    // 学院
    private String school;
    // 专业
    private String major;
    // 年龄
    private int age;
    // 性别
    private String gender;
    // 权限
    private int power;
    // 身份名称
    private String identity;
    // 密码
    private String password;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", power=" + power +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
