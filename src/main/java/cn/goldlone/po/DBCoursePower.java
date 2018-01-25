package cn.goldlone.po;

/**
 * Created by CN on 2017/12/17.
 */
public class DBCoursePower {
    // 课程号
    private int courseNo;
    // 权限
    private int power;

    public DBCoursePower(){}

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "DBCoursePower{" +
                "courseNo=" + courseNo +
                ", power=" + power +
                '}';
    }
}
