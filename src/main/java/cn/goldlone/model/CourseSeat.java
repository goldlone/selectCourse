package cn.goldlone.model;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/12/19.
 */
public class CourseSeat {
    private String stuNo;
    private String stuName;
    private int grade;
    private String school;
    private int no;
    private String name;
    private String classroom;
    private String teacher;
    private int time;
    private int stage;
    private Timestamp startSelectDate;
    private Timestamp endSelectDate;
    private int seatNo;

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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getStartSelectDate() {
        return startSelectDate;
    }

    public void setStartSelectDate(Timestamp startSelectDate) {
        this.startSelectDate = startSelectDate;
    }

    public Timestamp getEndSelectDate() {
        return endSelectDate;
    }

    public void setEndSelectDate(Timestamp endSelectDate) {
        this.endSelectDate = endSelectDate;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "CourseSeat{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", grade=" + grade +
                ", school='" + school + '\'' +
                ", no=" + no +
                ", name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", time=" + time +
                ", stage=" + stage +
                ", startSelectDate=" + startSelectDate +
                ", endSelectDate=" + endSelectDate +
                ", seatNo=" + seatNo +
                '}';
    }
}
