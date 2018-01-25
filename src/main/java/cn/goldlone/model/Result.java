package cn.goldlone.model;

/**
 * API请求返回
 * Created by CN on 2018/1/25.
 */
public class Result<T> {
    // 返回码
    private Integer code;
    // 返回消息
    private String msg;
    // 返回数据
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
