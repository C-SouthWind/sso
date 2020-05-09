package com.chj.interceptor;

import com.chj.service.LoginService;
import com.chj.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 23:03
 * @params :  第三步  配置拦截
 */
@Service
public class LoginInterceptor  implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 执行拦截器的代码就是在这里去实现的
        // 这个拦截器拦截的就是/orderPhone路径，拦截的目的就是为了判断用户是否处于登录状态
        // 从cookie中获取redisKey的信息(cookie的value就是redis的key)
        String redisKey = CookieUtil.getCookieValue(request, "COOKIE_KEY");
        // 判断redisKey是否存在
        if(null == redisKey || "".equals(redisKey)) {
            // 用户根本就没有处于登录状态
            // 需要跳转到登录项目中的登录页面让其登录
            // 登录成功之后返回上一个页面，所以必须要获取到当前页面的url地址
            StringBuffer currentPage = request.getRequestURL();
            response.sendRedirect("http://127.0.0.1:8081/turnLoginPage?returnUrl="+currentPage);
        } else {
            // 验证数据库中是否有这个用户
            String userString = loginService.checkLogin(redisKey);
            // 判断userString是否为null
            if(null != userString && !"".equals(userString)) {
                // 说明确实已经处于登录状态了
                return true;
            }

        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
