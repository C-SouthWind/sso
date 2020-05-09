package com.chj.service;

import com.chj.mapper.UserMapper;
import com.chj.model.User;
import com.chj.utils.CookieUtil;
import com.chj.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 21:28
 * @params :    第五步
 */
@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;
    /** 方法描述 
    * @Description: 执行登录操作
    * @Param: [user, reidsService, response, request]
    * @return: java.lang.String
    * @Author: chj
    * @Date: 2020/5/9
    */
    public String doLogin(User user, ReidsService reidsService, HttpServletResponse response, HttpServletRequest request){
        //从数据库查询用户是否登录
        User u = userMapper.selectUserByUsernamePassword(user);
        //判断是否为空
        if (null != u) {
            //已经是登录状态  清空密码
            u.setPassword(null);
            //把用户信息存到reids中
            String userString = JSONUtil.toJsonString(u);
            String setResult = reidsService.set("sessionId", userString);
            //判断存入redis是否成功
            if ("OK".equals(setResult)) {
                //redis存入成功 把redis的key存到cookie中
                CookieUtil.setCookie(request,response,"COOKIE_KEY","sessionId");
                return "success";
            }
        }
        return null;
    }
    /** 方法描述
    * @Description: 检测用户是否处于登录状态
    * @Param: [redisKey, reidsService]
    * @return: java.lang.String
    * @Author: chj
    * @Date: 2020/5/9
    */
    public String checkLogin(String redisKey,ReidsService reidsService){
        return reidsService.get(redisKey);
    }

}
