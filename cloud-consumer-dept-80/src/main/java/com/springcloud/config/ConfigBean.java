package com.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced  // ribbon配置负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    // 修改默认的轮询算法
//    @Bean
//    public IRule myRule() {
//        return new RandomRule();
//    }
}
