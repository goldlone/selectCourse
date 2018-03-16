package cn.goldlone.po;

import java.util.Date;


/**
 * Student
 * Created by CN on 2017/12/17.
 */
public class DBStudent {
    // 学号/工号
    private String no;
    // 姓名
    private String name;
    // 基层党组织编号
    private int schoolNo;
    // 性别
    private String gender;
    // 民族
    private String nation;
    // 出生日期
    private Date birth;
    // 类别
    private String type;
    // 年级
    private int grade;
    // 职务
    private String position;
    // 申请入党时间
    private Date applyDate;
    // 定为积极分子时间
    private Date beActivistDate;
    // 定为发展对象初步人选时间
    private Date beDevelopDate;
    // 权限
    private int power;
    // 密码
    private String password;
    // 批次
    private int batch;

    public DBStudent() {
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

    public int getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(int schoolNo) {
        this.schoolNo = schoolNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getBeActivistDate() {
        return beActivistDate;
    }

    public void setBeActivistDate(Date beActivistDate) {
        this.beActivistDate = beActivistDate;
    }

    public Date getBeDevelopDate() {
        return beDevelopDate;
    }

    public void setBeDevelopDate(Date beDevelopDate) {
        this.beDevelopDate = beDevelopDate;
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

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "DBStudent{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", schoolNo=" + schoolNo +
                ", gender='" + gender + '\'' +
                ", nation='" + nation + '\'' +
                ", birth=" + birth +
                ", type='" + type + '\'' +
                ", grade=" + grade +
                ", position='" + position + '\'' +
                ", applyDate=" + applyDate +
                ", beActivistDate=" + beActivistDate +
                ", beDevelopDate=" + beDevelopDate +
                ", power=" + power +
                ", password='" + password + '\'' +
                ", batch=" + batch +
                '}';
    }
}
