package cn.goldlone.controller;

import cn.goldlone.Properties;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 视图层控制器
 * Created by CN on 2018/1/30.
 */
@Controller
public class ViewController implements ErrorController {
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        System.out.println(request.getSession().getAttribute(Properties.LOGIN_POWER));
        Integer power = (Integer)request.getSession().getAttribute(Properties.LOGIN_POWER);
        System.out.println("默认："+power);
        if(power!=null) {
            if (power > 1) {
                return "/stu";
            } else {
                return "/tea";
            }
        }
        return "/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/error")
    @Override
    public String getErrorPath() {
        return "/error";
    }

}
