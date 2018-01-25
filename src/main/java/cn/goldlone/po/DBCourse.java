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
    // 学时
    private int time;

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

    @Override
    public String toString() {
        return "DBCourse{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
