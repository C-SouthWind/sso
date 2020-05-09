package com.chj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 20:16
 * @params :
 */
@SpringBootApplication
@MapperScan("com.chj.mapper")
public class Application8081 {
    public static void main(String[] args) {
        SpringApplication.run(Application8081.class,args);
    }
}
