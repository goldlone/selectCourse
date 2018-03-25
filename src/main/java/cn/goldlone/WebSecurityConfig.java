package cn.goldlone;

import cn.goldlone.utils.ResultUtil;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CN on 2018/2/3.
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    /**
     * 添加注册拦截器来组成一个拦截链，以及用于添加拦截规则和排除不用的拦截
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getLoginInterceptor());
        addInterceptor.addPathPatterns("/*");
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/loginVerify");
        super.addInterceptors(registry);
    }


    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * 登录拦截器
     */
    private class LoginInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handler)
                throws IOException {
            HttpSession session = request.getSession();
            System.out.println("拦截器："+session.getAttribute(Properties.LOGIN_POWER));
            System.out.println("拦截器："+request.getSession().getMaxInactiveInterval());
            // 判断是否已有该用户登录的session
            if(session.getAttribute(Properties.LOGIN_POWER) != null){
                return true;
            }

            // 跳转到登录页
            response.sendRedirect("/sc/login");
            return false;


//            session.setAttribute(Properties.LOGIN_NO, "123");
//            session.setAttribute(Properties.LOGIN_POWER, 0);
//            session.setAttribute(Properties.LOGIN_SCHOOL_NO, 2);
//            return true;
        }
    }
}
