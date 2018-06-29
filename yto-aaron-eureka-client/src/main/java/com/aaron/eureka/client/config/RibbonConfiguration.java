package com.aaron.eureka.client.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RibbonConfiguration
 * @Description : ribbon配置类
 * @Author Aaron
 * @Date 2018/6/25
 * @Version 1.0
 */

@Configuration
@ExcludeFromComponentScan
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {

        // 负载均衡规则，随机策略
        //return new RandomRule();

        //轮询策略
        //return new RoundRobinRule();

        //最佳策略
       // return  new BestAvailableRule();

        //重试策略
        return  new RetryRule();
    }
}