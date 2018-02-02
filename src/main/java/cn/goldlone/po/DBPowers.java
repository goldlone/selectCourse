package cn.goldlone.po;

/**
 * Powers
 * Created by CN on 2017/12/18.
 */
public class DBPowers {
    // 权限编号
    private int no;
    // 身份名称
    private String identity;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "DBPowers{" +
                "no=" + no +
                ", identity='" + identity + '\'' +
                '}';
    }
}
