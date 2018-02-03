package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.service.StudentService;
import cn.goldlone.utils.CheckUtils;
import cn.goldlone.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CN on 2018/2/3.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private StudentService ss;


    /**
     * 登录
     * @param request
     * @param model
     * @param no
     * @param password
     * @param type
     * @return
     */
    @RequestMapping("/loginVerify")
    public String loginVerify(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model,
                              String no,
                              String password,
                              Integer type)
            throws IOException {
        if(CheckUtils.IsEffectiveStr(new String[]{no, password}) && type!=null) {
            System.out.println(password);
            if (type == 1) {
                Result result = ss.login(request, no, password);
                if(result.getCode().equals(ResultUtils.CODE_SUCCESS)) {
                    // 登录成功
                    model.addAttribute("tips", "学员，你好");
                    response.sendRedirect("/");
                    return "/index";
                } else if(result.getCode().equals(ResultUtils.CODE_RESULT_NOT_EXIST)) {
                    model.addAttribute("tips", "用户不存在");
                } else if (result.getCode().equals(ResultUtils.CODE_OPERATE_FAIL)){
                    model.addAttribute("tips", "密码错误");
                } else {
                    model.addAttribute("tips", "未知错误");
                }
            } else if (type == 2) {
                Result result = ss.loginAdmin(request, no, password);
                if(result.getCode().equals(ResultUtils.CODE_SUCCESS)) {
                    // 登录成功
                    model.addAttribute("tips", "管理员，你好");
                    response.sendRedirect("/");
                    return "/index";
                } else if(result.getCode().equals(ResultUtils.CODE_RESULT_NOT_EXIST)) {
                    model.addAttribute("tips", "用户不存在");
                } else if (result.getCode().equals(ResultUtils.CODE_OPERATE_FAIL)){
                    model.addAttribute("tips", "密码错误");
                } else {
                    model.addAttribute("tips", "未知错误");
                }
            } else {
                model.addAttribute("tips", "错误的角色类型");
            }
        } else {
            model.addAttribute("tips", "参数缺失");
        }
        return "/login";
    }

    /**
     * 用户修改密码
     * @param password
     * @param newPassword
     * @return
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(HttpServletRequest request, String password, String newPassword) {
        String stuNo = (String) request.getSession().getAttribute("stuNo");
        return ss.updatePassword(stuNo, password, newPassword);
    }
}
