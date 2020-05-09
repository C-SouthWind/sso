package com.chj.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 23:01
 * @params : 第二步
 */
@Controller
public class OrderController {
    /** 方法描述 
    * @Description:  跳转到下单页面
    * @Param: []
    * @return: java.lang.String
    * @Author: chj
    * @Date: 2020/5/9
    */
    @GetMapping("/orderPhone")
    public String turnOrderPage() {
        return "order";
    }
}
