package cn.goldlone.po;

import cn.goldlone.model.Course;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/12/17.
 */
public class DBCoursePlus {
    private int courseNo;
    private int stage;
    private Timestamp startSelectDate;
    private Timestamp endSelectDate;

    public DBCoursePlus(){}

    public DBCoursePlus(Course course){
        this.courseNo = course.getNo();
        this.stage = course.getStage();
        this.startSelectDate = course.getStartSelectDate();
        this.endSelectDate = course.getEndSelectDate();
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
        return "DBCoursePlus{" +
                "courseNo=" + courseNo +
                ", stage=" + stage +
                ", startSelectDate=" + startSelectDate +
                ", endSelectDate=" + endSelectDate +
                '}';
    }
}
