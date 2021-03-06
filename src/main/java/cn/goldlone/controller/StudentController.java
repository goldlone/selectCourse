package cn.goldlone.controller;

import cn.goldlone.Properties;
import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
import cn.goldlone.po.DBAdmin;
import cn.goldlone.po.DBStudent;
import cn.goldlone.service.StudentService;
import cn.goldlone.utils.CheckUtils;
import cn.goldlone.utils.ExcelUtil;
import cn.goldlone.utils.ResultUtil;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CN on 2018/1/26.
 */
@RestController
public class StudentController extends BaseController {

    @Autowired
    private StudentService ss;

    /**
     * 获取全部批次信息
     * @return
     */
    @PostMapping("/stu/batch")
    public Result getBatch() {
        return ss.getBatch();
    }

    /**
     * 单个录入管理员信息
     * @param admin
     * @return
     */
    @PostMapping("/admin/add")
    public Result addAdmin(DBAdmin admin) {
        System.out.println(admin);
        if(!CheckUtils.IsEffectiveStr(new String[]{admin.getNo(), admin.getName()})) {
            return ResultUtil.error(ResultUtil.CODE_PARAMS_LOSE, "缺少必要的参数");
        }
        if(admin.getSchoolNo()==0) {
            return ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "不允许添加超级管理员");
        }return ss.addAdmin(admin);

    }

    /**
     * 删除管理员
     * @param no
     * @return
     */
    @PostMapping("/admin/delete")
    public Result deleteAdmin(String no) {
        return ss.deleteAdmin(no);
    }

    /**
     * 获取全部管理员信息
     * @return
     */
    @PostMapping("/admin/list")
    public Result getAllAdminInfo() {
        return ss.getAllAdminInfo();
    }

    /**
     * 获取基层党组织名称
     * @return
     */
    @PostMapping("/getSchools")
    public Result getSchools() {
        return ss.getSchools();
    }

    /**
     * 获取全部身份信息
     * @return
     */
    @PostMapping("/getPowers")
    public Result getPowers() {
        return ss.getPowers();
    }

    /**
     * 导出会员录入模板表
     * @param response
     * @return
     */
    @GetMapping("/stu/exportModel")
    public Result exportStuInfoModel(HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "importTemplate.xls");
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        Result result = null;
        try {
            os = response.getOutputStream();
//            File file = ExcelUtil.exportStuModel();
            File file = new File("./stuModel.xls");
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new Result();
    }

    /**
     * 添加单个学生信息
     * @param stu
     * @return
     */
    @PostMapping("/stu/addOne")
    public Result addOneStudent(DBStudent stu) {
        return ss.addStudent(stu);
    }

    /**
     * 添加学生
     * @param file
     * @return
     */
    @PostMapping("/stu/add")
    public Result addStudent(@RequestParam("file") MultipartFile file) {
        Result result = null;
        try {
            List<Student> list = ExcelUtil.importStuInfo(file);
            List<String> errorList = new ArrayList<>();
            for(Student stu: list) {
                if(ss.getStuInfoSelf(stu.getNo()).getData() != null) {
                    errorList.add("【失败】："+stu.getNo()+"-"+stu.getName()+"-该学员已存在");
                    continue;
                }
                try {
                    ss.addStudent(stu);
                } catch (Exception e) {
                    e.printStackTrace();
                    errorList.add("【失败】："+stu.getNo()+"-"+stu.getName()+"-数据格式有问题");
                }
            }
            result = ResultUtil.success("如果data为空数组，则插入全部正确，反之，data存储错误学生列表", errorList);
        } catch (IOException | BiffException e) {
            e.printStackTrace();
            result = ResultUtil.error(ResultUtil.CODE_OPERATE_FAIL, "文件格式有误");
        }
        return result;
    }

    /**
     * 删除学员信息
     * @param no
     * @return
     */
    @PostMapping("/stu/delete")
    public Result deleteStudent(String no, int batch) {
        return ss.deleteStudent(no, batch);
    }

//    /**
//     * 查询学员权限
//     * @param stuNo
//     * @return
//     */
//    @PostMapping("/stu/power")
//    public Result getStuPower(String stuNo) {
//        return ss.getStuPower(stuNo);
//    }

//    /**
//     * 获取全部学员信息
//     * @return
//     */
//    @PostMapping("/stu/allInfo")
//    public Result getAllStuInfo() {
//        return ss.getAllStuInfo();
//    }

    /**
     *  获取某基层党组织的全部学员信息
     * @param schoolNo
     * @return
     */
    @PostMapping("/stu/schoolInfo")
    public Result getStudentInfoBySchoolNo(int schoolNo, int batch, HttpServletRequest request) {
//        int power = (int) request.getSession().getAttribute(Properties.LOGIN_POWER);
//        int schoolNo1 = (int) request.getSession().getAttribute(Properties.LOGIN_SCHOOL_NO);
//        if(power==0 || (power==1 && schoolNo==schoolNo1))
            return ss.getStudentInfoBySchoolNo(schoolNo, batch);

//        return ResultUtil.error(ResultUtil.CODE_NO_POWER, "权限不足");
    }

    /**
     * 用户获取自身的信息
     * @param request
     * @return
     */
    @PostMapping("/stu/info")
    public Result getMyselfInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer power = (Integer) session.getAttribute(Properties.LOGIN_POWER);
        String no = (String) session.getAttribute(Properties.LOGIN_NO);
        if(power>1)
            return ss.getStuInfoSelf(no);
        else
            return ss.getAdminInfoByNo(no);
    }

    /**
     * 查询某学员的信息
     * @param stuNo
     * @return
     */
    @PostMapping("/stu/infoByNo")
    public Result getStuInfoByNo(String stuNo) {
        return ss.getStuInfoByNo(stuNo);
    }

    /**
     * 根据部分姓名查询学员信息
     * @param name
     * @return
     */
    @PostMapping("/stu/infoByName")
    public Result getStuInfoByName(String name) {
        return ss.getStuInfoByName(name);
    }

    /**
     * 管理员修改学员信息
     * @param stu
     * @return
     */
    @PostMapping("/stu/updateInfoByAdmin")
    public Result updateStuInfoAdmin(DBStudent stu) {
        return ss.updateStuInfoAdmin(stu);
    }

    /**
     * 学员自己修改信息
     * @param stu
     * @return
     */
    @PostMapping("/stu/updateInfoBySelf")
    public Result updateStuInfoSelf(DBStudent stu) {
        System.out.println(stu);
        return ss.updateStuInfoSelf(stu);
    }

    /**
     * 一批学员结束党课
     * @return
     */
    @PostMapping("/stu/endCourse")
    public Result endCourse() {
        return ss.endCourse();
    }


    /**
     * 提交反馈信息
     * @return
     */
    @PostMapping("/stu/feedback")
    public Result feedback(HttpServletRequest request, String content) {
        if(content==null || "".equals(content))
            return ResultUtil.error(ResultUtil.CODE_PARAMS_LOSE, "内容不能不能空");
        String stuNo = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
//        String stuNo = "123";
        return ss.feedback(stuNo, content);
    }

    /**
     * 获取反馈信息
     * @return
     */
    @PostMapping("/stu/getFeedback")
    public Result getFeedback() {
        return ss.getFeedback();
    }

    /**
     * 处理反馈信息
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/stu/dealFeedback")
    public Result dealFeedback(HttpServletRequest request, long id) {
        String no = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
//        String no = "123";
        return ss.dealFeedback(id, no);
    }

}
