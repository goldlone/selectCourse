package cn.goldlone.po;

import cn.goldlone.model.Course;

/**
 * Created by CN on 2017/12/17.
 */
public class DBCourse {
    // 课程编号
    private int no;
    // 课程名
    private String name;
    // 教室
    private String classroom;
    // 教师
    private String teacher;
    // 学时
    private int time;

    public DBCourse(){}

    public DBCourse(Course course) {
        this.no = course.getNo();
        this.name = course.getName();
        this.classroom = course.getClassroom();
        this.teacher = course.getTeacher();
        this.time = course.getTime();
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

    @Override
    public String toString() {
        return "DBCourse{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", time=" + time +
                '}';
    }
}
