package com.aaron.eureka.client.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName: DcController
 * @Description : eureka服务提供者控制层
 * @Author Aaron
 * @Date 2018/6/25
 * @Version 1.0
 */

@RestController
public class DcController {
    private final Logger logger = LoggerFactory.getLogger(DcController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 服务注册
     */
    @Autowired
    private Registration registration;

    /**
     * HTTP 访问操作类
     */
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/customer")
    @HystrixCommand(fallbackMethod = "failBack")
    public String customer() {
        return restTemplate.getForObject("http://ONE-CLOUD-CLIENT/provider", String.class);
    }



    @RequestMapping("/provider")
    public String provider() {
        ServiceInstance instance = serviceInstance();
        logger.info("provider service, host = " + instance.getHost() + ", service_id = " + instance.getServiceId());
        return "Hello,Provider!";
    }

    /**
     * 获取当前服务的服务实例
     * @return ServiceInstance
     */
    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 断路返回
     * @return String
     * @Title: failBack
     * @Description:断路返回
     * @author: Aaron
     */
    public String failBack() {
        return "Error occurred ！";
    }


}
