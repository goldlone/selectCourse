package cn.goldlone.model;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/12/19.
 */
public class Course {
    // 课程号
    private int no;
    // 课程名
    private String name;
    // 期号
    private int stage;
    // 教室
    private String classroom;
    // 教师
    private String teacher;
    // 课程开始时间
    private Timestamp startDateTime;
    // 课程结束时间
    private Timestamp endDateTime;
    // 课程开始选课时间
    private Timestamp startSelectDateTime;
    // 课程结束选课时间
    private Timestamp endSelectDateTime;
    // 学时
    private int time;


    public Course() {}

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

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Timestamp getStartSelectDateTime() {
        return startSelectDateTime;
    }

    public void setStartSelectDateTime(Timestamp startSelectDateTime) {
        this.startSelectDateTime = startSelectDateTime;
    }

    public Timestamp getEndSelectDateTime() {
        return endSelectDateTime;
    }

    public void setEndSelectDateTime(Timestamp endSelectDateTime) {
        this.endSelectDateTime = endSelectDateTime;
    }

    @Override
    public String toString() {
        return "Course{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", stage=" + stage +
                ", classroom='" + classroom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", startSelectDateTime=" + startSelectDateTime +
                ", endSelectDateTime=" + endSelectDateTime +
                ", time=" + time +
                '}';
    }
}
