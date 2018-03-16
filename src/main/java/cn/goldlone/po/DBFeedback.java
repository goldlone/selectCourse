package cn.goldlone.po;

import java.sql.Timestamp;

public class DBFeedback {
    // 反馈信息
    private String content;
    // 反馈者
    private String publicer;
    // 发布时间
    private Timestamp publicTime;
    // 是否处理
    private int isDeal;
    // 处理时间
    private Timestamp dealTime;
    // 处理者
    private String dealMan;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublicer() {
        return publicer;
    }

    public void setPublicer(String publicer) {
        this.publicer = publicer;
    }

    public Timestamp getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Timestamp publicTime) {
        this.publicTime = publicTime;
    }

    public int getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(int isDeal) {
        this.isDeal = isDeal;
    }

    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealMan() {
        return dealMan;
    }

    public void setDealMan(String dealMan) {
        this.dealMan = dealMan;
    }

    @Override
    public String toString() {
        return "DBFeedback{" +
                "content='" + content + '\'' +
                ", publicer='" + publicer + '\'' +
                ", publicTime=" + publicTime +
                ", isDeal=" + isDeal +
                ", dealTime=" + dealTime +
                ", dealMan='" + dealMan + '\'' +
                '}';
    }
}
