package cn.goldlone.controller;

import cn.goldlone.Properties;
import cn.goldlone.utils.CheckUtils;
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
        if(power!=null) {
            if (power > 1) {
                model.addAttribute("tips", "学员，你好");
                return "/index";
            } else {
                model.addAttribute("tips", "管理员，你好");
                return "/index";
            }
        }
        return "/index";
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


    @GetMapping("/upload")
    public String upload() {
        return "/upload";
    }

}
