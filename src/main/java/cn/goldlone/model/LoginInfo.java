package cn.goldlone.model;

/**
 * Created by CN on 2017/12/18.
 */
public class LoginInfo {
    // 学号
    private String stuNo;
    // 密码
    private String password;
    // 权限
    private int power;

    public LoginInfo() {
        this.power = -1;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
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

    @Override
    public String toString() {
        return "LoginInfo{" +
                "stuNo='" + stuNo + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                '}';
    }
}
