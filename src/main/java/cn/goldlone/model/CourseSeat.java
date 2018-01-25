package cn.goldlone.model;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/12/19.
 */
public class CourseSeat {
    // 学号
    private String stuNo;
    // 学生姓名
    private String stuName;
    // 年级
    private int grade;
    // 基层组织
    private String school;
    // 课程号
    private int courseNo;
    // 课程名
    private String courseName;
    // 课程期号
    private int stage;
    // 教室
    private String classroom;
    // 教师
    private String teacher;
    // 学时
    private int time;
    // 课程开始时间
    private Timestamp startDateTime;
    // 课程结束时间
    private Timestamp endDateTime;
    // 座位号
    private int seatNo;
    // 获取的学分
    private int acquireTime;

    public CourseSeat() {
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(int acquireTime) {
        this.acquireTime = acquireTime;
    }

    @Override
    public String toString() {
        return "CourseSeat{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", grade=" + grade +
                ", school='" + school + '\'' +
                ", courseNo=" + courseNo +
                ", courseName='" + courseName + '\'' +
                ", stage=" + stage +
                ", classroom='" + classroom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", time=" + time +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", seatNo=" + seatNo +
                ", acquireTime=" + acquireTime +
                '}';
    }
}
