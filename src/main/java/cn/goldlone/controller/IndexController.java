package cn.goldlone.controller;

import cn.goldlone.Properties;
import cn.goldlone.model.Result;
import cn.goldlone.service.StudentService;
import cn.goldlone.utils.CheckUtils;
import cn.goldlone.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            if (type == 1) {
                Result result = ss.login(request, no, password);
                if(result.getCode().equals(ResultUtil.CODE_SUCCESS)) {
                    // 登录成功
                    response.sendRedirect("/sc/");
                    return "/stu";
                } else if(result.getCode().equals(ResultUtil.CODE_RESULT_NOT_EXIST)) {
                    model.addAttribute("tips", "用户不存在");
                } else if (result.getCode().equals(ResultUtil.CODE_OPERATE_FAIL)){
                    model.addAttribute("tips", "密码错误");
                } else {
                    model.addAttribute("tips", "未知错误");
                }
            } else if (type == 2) {
                Result result = ss.loginAdmin(request, no, password);
                if(result.getCode().equals(ResultUtil.CODE_SUCCESS)) {
                    // 登录成功
                    response.sendRedirect("/sc/");
                    return "/tea";
                } else if(result.getCode().equals(ResultUtil.CODE_RESULT_NOT_EXIST)) {
                    model.addAttribute("tips", "用户不存在");
                } else if (result.getCode().equals(ResultUtil.CODE_OPERATE_FAIL)) {
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
     * 学员修改密码
     * @param password
     * @param newPassword
     * @return
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public Result updatePassword(HttpServletRequest request, String password, String newPassword) {
        String stuNo = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
        return ss.updatePassword(stuNo, password, newPassword);
    }

    /**
     * 管理员修改密码
     * @param password
     * @param newPassword
     * @return
     */
    @PostMapping("/updateAdminPassword")
    @ResponseBody
    public Result updateAdminPassword(HttpServletRequest request, String password, String newPassword) {
        String no = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
        return ss.updateAdminPassword(no, password, newPassword);
    }


    /**
     * 获取当前登录用户的权限
     * @param request
     * @return
     */
    @PostMapping("/power")
    @ResponseBody
    public Result getPower(HttpServletRequest request) {
        Integer power = (Integer)request.getSession().getAttribute(Properties.LOGIN_POWER);
        return ResultUtil.success("获取成功", power);
    }

    /**
     * 注销登录
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(Properties.LOGIN_POWER);
        session.removeAttribute(Properties.LOGIN_NO);
        session.removeAttribute(Properties.LOGIN_SCHOOL_NO);
        response.sendRedirect("/sc/login");
    }

}
