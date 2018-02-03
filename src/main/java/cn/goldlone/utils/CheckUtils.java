package cn.goldlone.utils;

import cn.goldlone.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * 检查工具类
 * Created by CN on 2018/1/31.
 */
public class CheckUtils {

    /**
     * 判断是否已登录
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        if(request.getSession().getAttribute(Properties.LOGIN_POWER)!=null)
            return true;
        return false;
    }

    /**
     * 判断是否是一个有效的字符串
     * @param str
     * @return
     */
    public static boolean IsEffectiveStr(String str) {
        if(str!=null && !"".equals(str))
            return true;
        else
            return false;
    }

    /**
     * 检查一个字符串数组是否是有效的字符串
     * @param arr
     * @return
     */
    public static boolean IsEffectiveStr(String[] arr) {
        for(String str: arr)
            if(str==null || "".equals(str))
                return false;
        return true;
    }
}
