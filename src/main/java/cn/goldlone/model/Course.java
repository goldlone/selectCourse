package cn.goldlone.model;

import cn.goldlone.po.DBCourse;
import cn.goldlone.po.DBCoursePlus;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/12/19.
 */
public class Course {
    private int no;
    private String name;
    private String classroom;
    private String teacher;
    private int time;
    private int stage;
    private Timestamp startSelectDate;
    private Timestamp endSelectDate;

    public Course() {}

    public Course(DBCourse course) {
        this.no = course.getNo();
        this.name = course.getName();
        this.classroom = course.getClassroom();
        this.teacher = course.getTeacher();
        this.time = course.getTime();
    }

    public Course(DBCoursePlus course) {
        this.no = course.getCourseNo();
        this.stage = course.getStage();
        this.startSelectDate = course.getStartSelectDate();
        this.endSelectDate = course.getEndSelectDate();
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

    @Override
    public String toString() {
        return "Course{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", time=" + time +
                ", stage=" + stage +
                ", startSelectDate=" + startSelectDate +
                ", endSelectDate=" + endSelectDate +
                '}';
    }
}
