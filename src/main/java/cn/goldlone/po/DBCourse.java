package cn.goldlone.po;

import cn.goldlone.model.Course;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/12/17.
 */
public class DBCourse {
    // 课程编号
    private int no;
    // 课程名
    private String name;
    // 学时
    private int time;
    // 课程开始选课时间
    private Timestamp startSelectDateTime;
    // 课程结束选课时间
    private Timestamp endSelectDateTime;

    public DBCourse(){}

    public DBCourse(String name, int time) {
        this.name = name;
        this.time = time;
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
        return "DBCourse{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", startSelectDateTime=" + startSelectDateTime +
                ", endSelectDateTime=" + endSelectDateTime +
                '}';
    }
}
