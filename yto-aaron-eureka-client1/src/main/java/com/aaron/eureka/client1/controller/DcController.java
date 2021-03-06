package com.aaron.eureka.client1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

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



    @RequestMapping("/provider")
    public String provider() {
        ServiceInstance instance = serviceInstance();
        logger.info("provider service, host = " + instance.getHost() + ", service_id = " + instance.getServiceId());
        try {
            // 模拟连接超时
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello,Provider11";
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



}
