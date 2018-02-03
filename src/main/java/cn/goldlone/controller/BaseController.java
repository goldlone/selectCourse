package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.service.StudentService;
import cn.goldlone.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
