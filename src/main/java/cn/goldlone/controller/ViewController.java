package cn.goldlone.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图层控制器
 * Created by CN on 2018/1/30.
 */
@Controller
public class ViewController implements ErrorController {
    @GetMapping("/")
    public String index() {
        return "/index";
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
