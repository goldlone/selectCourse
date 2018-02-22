package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.utils.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by CN on 2018/1/28.
 */
public class BaseController {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return ResultUtils.error(ResultUtils.CODE_EXCEPTION, "【异常】："+e.getMessage());
    }


}
