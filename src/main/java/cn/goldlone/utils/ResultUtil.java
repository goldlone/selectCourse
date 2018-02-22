package cn.goldlone.utils;

import cn.goldlone.model.Result;

/**
 * API 请求返回消息工具类
 * Created by CN on 2018/1/25.
 */
public class ResultUtil {

    /****  请求成功  ****/
    public static final Integer CODE_SUCCESS = 1001;
    /****  请求结果不存在  ****/
    public static final Integer CODE_RESULT_NOT_EXIST = 2001;
    /****  操作失败  ****/
    public static final Integer CODE_OPERATE_FAIL = 2002;
    /****  权限不够  ****/
    public static final Integer CODE_NO_POWER = 2003;
    /****  登录失效  ****/
    public static final Integer CODE_OUT_LOGIN = 2004;
    /****  请求参数缺失 ****/
    public static final Integer CODE_PARAMS_LOSE = 2005;
    /****  操作未选课程  ****/
    public static final Integer CODE_FAIL_NO_SELECT = 2006;

    /****  异常代码  ****/
    public static final Integer CODE_EXCEPTION = 3001;

    /**
     * 成功返回
     * @param msg
     * @param data
     * @return
     */
    public static Result success(String msg, Object data) {
        Result res = new Result();
        res.setCode(ResultUtil.CODE_SUCCESS);
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
        res.setCode(ResultUtil.CODE_SUCCESS);
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
