package cn.goldlone.model;

/**
 * 登录信息
 * Created by CN on 2017/12/18.
 */
public class LoginInfo {
    // 学号
    private String no;
    // 密码
    private String password;
    // 权限
    private int power;
    // 基层党组织编号
    private int schoolNo;

    public LoginInfo() {
        this.power = -1;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(int schoolNo) {
        this.schoolNo = schoolNo;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "no='" + no + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                ", schoolNo=" + schoolNo +
                '}';
    }
}
