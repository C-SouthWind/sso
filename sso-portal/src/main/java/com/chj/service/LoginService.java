package com.chj.service;

import com.chj.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 23:03
 * @params :  第四步
 */
@Service
public class LoginService {
    /** 方法描述 
    * @Description: 唯一的作用也就是跳转到登录项目中
    * @Param: [redisKey]
    * @return: java.lang.String
    * @Author: chj
    * @Date: 2020/5/9
    */
    public String checkLogin(String redisKey) {
        return HttpClientUtil.doGet("http://127.0.0.1:8081/token/"+redisKey);
    }

}
