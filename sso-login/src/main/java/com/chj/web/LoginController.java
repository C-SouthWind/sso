package com.chj.web;

import com.chj.model.User;
import com.chj.service.LoginService;
import com.chj.service.ReidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 20:45
 * @params : 第四步
 */
@Controller
public class LoginController {
    @Autowired
    private ReidsService reidsService;
    @Autowired
    private LoginService loginService;
    
    /** 方法描述 
    * @Description: 执行登录操作
    * @Param: [user, request, response]
    * @return: java.lang.String
    * @Author: chj
    * @Date: 2020/5/9
    */
    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response){
        return loginService.doLogin(user,reidsService,response,request);
    }
    /** 方法描述
    * @Description: 这个方法是检测用户是否处于登录状态
     *              这里的callback不是咱们定义的，是当你使用jsonp的实话，ajax会默认发送一个回调callback
     *              也就是说controller如果是跨域必须要接收这个callback
     *              这个callback就是跨域的唯一标识符
    * @Param: [redisKey, callback]
    * @return: java.lang.Object
    * @Author: chj
    * @Date: 2020/5/9
    */
    @RequestMapping("/token/{redisKey}")
    @ResponseBody
    public Object checkLogin(@PathVariable String redisKey,String callback){
        //从redis中获取用户信息
        String userString = loginService.checkLogin(redisKey, reidsService);
        //判断用户信息是否为null
        if (null != userString && !"".equals(userString)) {
            //redis中有值
            //判断callback是否为null，callback为null说明是常规ajax调用 不为null 说明是特殊调用(跨域请求)
            if (null != callback && !"".equals(callback)) {
                //说明是跨域请求
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userString);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }else{
                //普通ajax请求
                return userString;
            }
        }
        return null;
    }
    /** 方法描述
    * @Description: 跳转到登录页面
    * @Param: [returnUrl, mpdelMap]
    * @return: java.lang.String
    * @Author: chj
    * @Date: 2020/5/9
    */
    @GetMapping("/turnLoginPage")
    public String turnLoginPage(String returnUrl, ModelMap mpdelMap){
        if (null != returnUrl && !"".equals(returnUrl)) {
            mpdelMap.addAttribute("returnUrl",returnUrl);
        }
        return "login";
    }

}


































