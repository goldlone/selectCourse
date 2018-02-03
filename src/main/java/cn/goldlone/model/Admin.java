package cn.goldlone.model;

/**
 * Created by CN on 2018/2/3.
 */
public class Admin {
    // 工号
    private String no;
    // 姓名
    private String name;
    // 基层党组织名称
    private String school;
    // 登录密码
    private String password;
    // 权限
    private int power;

    public Admin() {
    }

    public Admin(String no, String name, String school,
                 String password, int power) {
        this.no = no;
        this.name = name;
        this.school = school;
        this.password = password;
        this.power = power;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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
        return "Admin{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                '}';
    }
}
