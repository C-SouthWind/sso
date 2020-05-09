package com.chj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 20:46
 * @params :    第三步
 */
@Service
public class ReidsService {
    @Autowired
    private JedisCluster jedisCluster;
    public String get(String key){
        return jedisCluster.get(key);
    }
    public String set(String key,String value){
        return jedisCluster.set(key,value);
    }
}
