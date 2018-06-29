package com.aaron.eureka.client;

import com.aaron.eureka.client.config.ExcludeFromComponentScan;
import com.aaron.eureka.client.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@RibbonClient(name = "ONE-CLOUD-CLIENT", configuration = RibbonConfiguration.class)
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
public class ApplicationClient {

    /**
      * 开启负载均衡客户端
      * @Title: 开启负载均衡客户端
     * @Description:
      * @author: Aaron
      * @return RestTemplate
      */
    @Bean
    @LoadBalanced
   protected  RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationClient.class,args);
    }
}