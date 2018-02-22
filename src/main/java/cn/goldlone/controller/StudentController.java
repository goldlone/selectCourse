package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
import cn.goldlone.po.DBAdmin;
import cn.goldlone.po.DBStudent;
import cn.goldlone.service.StudentService;
import cn.goldlone.utils.CheckUtils;
import cn.goldlone.utils.ExcelUtils;
import cn.goldlone.utils.ResultUtils;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * 单个录入管理员信息
     * @param admin
     * @return
     */
    @PostMapping("/admin/add")
    public Result addAdmin(DBAdmin admin) {
        System.out.println(admin);
        if(CheckUtils.IsEffectiveStr(new String[]{admin.getNo(), admin.getName()})) {
            return ResultUtils.error(ResultUtils.CODE_PARAMS_LOSE, "缺少必要的参数");
        }
        return ss.addAdmin(admin);
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
            List<Student> list = ExcelUtils.importStuInfo(file);
            List<String> errorList = new ArrayList<>();
            for(Student stu: list) {
                try {
                    ss.addStudent(stu);
                } catch (Exception e) {
                    e.printStackTrace();
                    errorList.add("【失败】："+stu.getNo()+"-"+stu.getName()+"-数据格式有问题");
                }
            }
            result = ResultUtils.success("如果data为空数组，则插入全部正确，反之，data存储错误信息", errorList);
        } catch (IOException e) {
            e.printStackTrace();
            result = ResultUtils.error(ResultUtils.CODE_OPERATE_FAIL, "文件格式有误");
        } catch (BiffException e) {
            e.printStackTrace();
            result = ResultUtils.error(ResultUtils.CODE_OPERATE_FAIL, "文件格式有误");
        }
        return result;
    }

    /**
     * 查询学员权限
     * @param stuNo
     * @return
     */
    @PostMapping("/stu/power")
    public Result getStuPower(String stuNo) {
        return ss.getStuPower(stuNo);
    }

    /**
     * 获取全部学员信息
     * @return
     */
    @PostMapping("/stu/allInfo")
    public Result getAllStuInfo() {
        return ss.getAllStuInfo();
    }

    /**
     *  获取某基层党组织的全部学员信息
     * @param schoolNo
     * @return
     */
    @PostMapping("/stu/schoolInfo")
    public Result getStudentInfoBySchoolNo(int schoolNo) {
        return ss.getStudentInfoBySchoolNo(schoolNo);
    }

    /**
     * 用户获取自身的信息
     * @param request
     * @return
     */
    @PostMapping("/stu/info")
    public Result getMyselfInfo(HttpServletRequest request) {
//        Integer power = (Integer) request.getSession().getAttribute(Properties.LOGIN_POWER);
//        String no = (String) request.getSession().getAttribute(Properties.LOGIN_NO);
        String no = "456";
        Integer power = 2;
        if(power>1)
            return ss.getStuInfoByNo(no);
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
     * @param request
     * @param stu
     * @return
     */
    @PostMapping("/stu/updateInfoBySelf")
    public Result updateStuInfoSelf(HttpServletRequest request, DBStudent stu) {
        stu.setNo((String) request.getSession().getAttribute("stuNo"));
        return ss.updateStuInfoSelf(stu);
    }



}
