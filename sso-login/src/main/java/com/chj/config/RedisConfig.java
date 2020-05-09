package com.chj.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 20:25
 * @params : 第二步
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisProperties redisProperties;
    @Bean
    public JedisCluster jedisCluster(){
        String nodes = redisProperties.getNodes();
        String[] split = nodes.split(",");
        HashSet<HostAndPort> hostAndPortHashSet = new HashSet<HostAndPort>();
        for (String hostPort : split) {
            String[] ipPort = hostPort.split(":");
            HostAndPort hostAndPort = new HostAndPort(ipPort[0], Integer.parseInt(ipPort[1]));
            hostAndPortHashSet.add(hostAndPort);
        }
        return new JedisCluster(hostAndPortHashSet,Integer.parseInt(redisProperties.getCommandTimeout()),Integer.parseInt(redisProperties.getMaxAttempts()));
    }
}



















