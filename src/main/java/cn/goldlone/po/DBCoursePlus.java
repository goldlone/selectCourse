package cn.goldlone.po;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/12/17.
 */
public class DBCoursePlus {
    // 课程号
    private int courseNo;
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

    public DBCoursePlus(){}

    public DBCoursePlus(int stage, String classroom, String teacher, Timestamp startDateTime, Timestamp endDateTime) {
        this.stage = stage;
        this.classroom = classroom;
        this.teacher = teacher;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
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

    @Override
    public String toString() {
        return "DBCoursePlus{" +
                "courseNo=" + courseNo +
                ", stage=" + stage +
                ", classroom='" + classroom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}
