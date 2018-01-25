package cn.goldlone.po;

/**
 * Created by CN on 2017/12/17.
 */
public class DBSelectCourse {
    // 学号
    private String stuNo;
    // 课程号
    private int courseNo;
    // 期号
    private int stage;
    // 座位号
    private int seatNo;
    // 获取学时
    private int acquireTime;

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
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
        return "DBSelectCourse{" +
                "stuNo='" + stuNo + '\'' +
                ", courseNo=" + courseNo +
                ", stage=" + stage +
                ", seatNo=" + seatNo +
                ", acquireTime=" + acquireTime +
                '}';
    }
}
