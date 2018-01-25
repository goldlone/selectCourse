//package cn.goldlone.service.impl;
//
//import cn.goldlone.mapper.StudentMapper;
//import cn.goldlone.model.LoginInfo;
//import cn.goldlone.model.Student;
//import cn.goldlone.service.StudentService;
//import cn.goldlone.utils.MybatisUtils;
//import com.opensymphony.xwork2.ActionSupport;
//import com.opensymphony.xwork2.ModelDriven;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.struts2.ServletActionContext;
//import org.json.JSONObject;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by CN on 2017/12/18.
// */
//public class StudentServiceImpl extends ActionSupport implements StudentService,ModelDriven<Student> {
//
//    private Student stu = new Student();
//    private SqlSession sqlSession = null;
//    private StudentMapper studentMapper = null;
//    private String newPassword;
//
//    @Override
//    public String signin() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        try {
//            sqlSession = MybatisUtils.openSqlSession();
//            studentMapper = sqlSession.getMapper(StudentMapper.class);
//            LoginInfo info = studentMapper.getPassword(this.stu.getNo());
//            if(this.stu.getNo()==null ||
//                    "".equals(this.stu.getNo()) ||
//                    this.stu.getPassword()==null ||
//                    "".equals(this.stu.getPassword())) {
//                res.put("success", false);
//                res.put("reason", "学号和密码不可为空");
//            } else if(info == null) {
//                res.put("success", false);
//                res.put("reason", "用户不存在");
//            } else {
//                String password = info.getPassword();
//                if(password.equals(DigestUtils.sha256Hex(this.stu.getPassword()))) {
//                    res.put("success", true);
//                    res.put("reason", "登录成功");
//                    // 记录Session
//                    request.getSession().setMaxInactiveInterval(1800);
//                    request.getSession().setAttribute("stuNo", this.stu.getNo());
//                    request.getSession().setAttribute("power", info.getPower());
//                } else {
//                    res.put("success", false);
//                    res.put("reason", "密码错误");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            if(sqlSession!=null)
//                sqlSession.rollback();
//            res.put("success", false);
//            res.put("reason", "数据库出现错误");
//        } finally {
//            if(sqlSession != null)
//                sqlSession.close();
//            out.print(res.toString());
//            out.flush();
//            out.close();
//        }
//        return null;
//    }
//
//    @Override
//    public String logout() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        request.getSession().removeAttribute("stuNo");
//        request.getSession().removeAttribute("power");
//        return SUCCESS;
//    }
//
//    @Override
//    public String updatePassword() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        try {
//            sqlSession = MybatisUtils.openSqlSession();
//            studentMapper = sqlSession.getMapper(StudentMapper.class);
//            String stuNo = null;
//            if((stuNo=(String)request.getSession().getAttribute("stuNo")) == null)
//                return LOGIN;
//            LoginInfo li1 = studentMapper.getPassword(stuNo);
//            if(li1.getPassword().equals(stu.getPassword())) {
//                li1.setPassword(newPassword);
//                if(studentMapper.updatePassword(li1)>0)
//                    res.put("success", true);
//                else
//                    res.put("success", false);
//                res.put("reason", "返回成功");
//            } else {
//                res.put("success", false);
//                res.put("reason", "原密码错误");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            if(sqlSession!=null)
//                sqlSession.rollback();
//        } finally {
//            if(sqlSession != null)
//                sqlSession.close();
//            out.print(res.toString());
//            out.flush();
//            out.close();
//        }
//        return null;
//    }
//
//    @Override
//    public String getStudentInfo() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        try {
//            sqlSession = MybatisUtils.openSqlSession();
//            studentMapper = sqlSession.getMapper(StudentMapper.class);
//            res.put("success", true);
//            res.put("reason", "返回成功");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            if(sqlSession!=null)
//                sqlSession.rollback();
//        } finally {
//            if(sqlSession != null)
//                sqlSession.close();
//            out.print(res.toString());
//            out.flush();
//            out.close();
//        }
//        return null;
//    }
//
//    public String getAllStudentInfo() throws IOException{
//
//        return null;
//    }
//    @Override
//    public String updateStudentInfo() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        try {
//            sqlSession = MybatisUtils.openSqlSession();
//            studentMapper = sqlSession.getMapper(StudentMapper.class);
//            res.put("success", true);
//            res.put("reason", "返回成功");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            if(sqlSession!=null)
//                sqlSession.rollback();
//        } finally {
//            if(sqlSession != null)
//                sqlSession.close();
//            out.print(res.toString());
//            out.flush();
//            out.close();
//        }
//        return null;
//    }
//
//    @Override
//    public String updateStudentInfoByMaster() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        try {
//            sqlSession = MybatisUtils.openSqlSession();
//            studentMapper = sqlSession.getMapper(StudentMapper.class);
//            res.put("success", true);
//            res.put("reason", "返回成功");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            if(sqlSession!=null)
//                sqlSession.rollback();
//        } finally {
//            if(sqlSession != null)
//                sqlSession.close();
//            out.print(res.toString());
//            out.flush();
//            out.close();
//        }
//        return null;
//    }
//
//    @Override
//    public Student getModel() {
//        return this.stu;
//    }
//
//    public String getNewPassword() {
//        return newPassword;
//    }
//
//    public void setNewPassword(String newPassword) {
//        this.newPassword = newPassword;
//    }
//}
