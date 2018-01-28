package cn.goldlone.utils;

import cn.goldlone.model.Result;

/**
 * API 请求返回消息工具类
 * Created by CN on 2018/1/25.
 */
public class ResultUtils {

    /****  异常代码  ****/
    public static final Integer CODE_EXCEPTION = 5001;

    /**
     * 成功返回
     * @param msg
     * @param data
     * @return
     */
    public static Result success(String msg, Object data) {
        Result res = new Result();
        res.setCode(0);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    /**
     * 成功返回
     * @param msg
     * @return
     */
    public static Result success(String msg) {
        Result res = new Result();
        res.setCode(0);
        res.setMsg(msg);
        res.setData(null);
        return res;
    }

    /**
     * 错误异常返回
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(null);
        return res;
    }

}
