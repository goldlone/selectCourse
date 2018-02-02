package cn.goldlone.po;

/**
 * Admin
 * Created by CN on 2018/2/2.
 */
public class DBAdmin {
    // 工号
    private String no;
    // 姓名
    private String name;
    // 基层党组织编号
    private int schoolNo;
    // 登录密码
    private String password;
    // 权限
    private int power;

    public DBAdmin() {
    }

    public DBAdmin(String no, String name, int schoolNo,
                   String password, int power) {
        this.no = no;
        this.name = name;
        this.schoolNo = schoolNo;
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

    public int getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(int schoolNo) {
        this.schoolNo = schoolNo;
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
        return "DBAdmin{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", schoolNo=" + schoolNo +
                ", password='" + password + '\'' +
                ", power=" + power +
                '}';
    }
}
