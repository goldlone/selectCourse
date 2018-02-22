package cn.goldlone.controller;

import cn.goldlone.Properties;
import cn.goldlone.model.Result;
import cn.goldlone.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CN on 2018/2/3.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private StudentService ss;

//    /**
//     * 登录
//     * @param request
//     * @param model
//     * @param no
//     * @param password
//     * @param type
//     * @return
//     */
//    @RequestMapping("/loginVerify")
//    public String loginVerify(HttpServletRequest request,
//                              HttpServletResponse response,
//                              Model model,
//                              String no,
//                              String password,
//                              Integer type)
//            throws IOException {
//        if(CheckUtils.IsEffectiveStr(new String[]{no, password}) && type!=null) {
//            System.out.println(password);
//            if (type == 1) {
//                Result result = ss.login(request, no, password);
//                if(result.getCode().equals(ResultUtil.CODE_SUCCESS)) {
//                    // 登录成功
//                    model.addAttribute("tips", "学员，你好");
//                    response.sendRedirect("/sc/");
//                    return "/index";
//                } else if(result.getCode().equals(ResultUtil.CODE_RESULT_NOT_EXIST)) {
//                    model.addAttribute("tips", "用户不存在");
//                } else if (result.getCode().equals(ResultUtil.CODE_OPERATE_FAIL)){
//                    model.addAttribute("tips", "密码错误");
//                } else {
//                    model.addAttribute("tips", "未知错误");
//                }
//            } else if (type == 2) {
//                Result result = ss.loginAdmin(request, no, password);
//                if(result.getCode().equals(ResultUtil.CODE_SUCCESS)) {
//                    // 登录成功
//                    model.addAttribute("tips", "管理员，你好");
//                    response.sendRedirect("/sc/");
//                    return "/index";
//                } else if(result.getCode().equals(ResultUtil.CODE_RESULT_NOT_EXIST)) {
//                    model.addAttribute("tips", "用户不存在");
//                } else if (result.getCode().equals(ResultUtil.CODE_OPERATE_FAIL)){
//                    model.addAttribute("tips", "密码错误");
//                } else {
//                    model.addAttribute("tips", "未知错误");
//                }
//            } else {
//                model.addAttribute("tips", "错误的角色类型");
//            }
//        } else {
//            model.addAttribute("tips", "参数缺失");
//        }
//        return "/login";
//    }

    /**
     * 学员修改密码
     * @param password
     * @param newPassword
     * @return
     */
    @PostMapping("/updatePassword")
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
    public Result updateAdminPassword(HttpServletRequest request, String password, String newPassword) {
        String no = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
        return ss.updateAdminPassword(no, password, newPassword);
    }


}
