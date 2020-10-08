package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.pojo.Dept;
import com.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept==null) {
            throw new RuntimeException("无法找到id"+id);
        }
        return dept;
    }

    public Dept hystrixGet(@PathVariable("id") Long id) {
        Dept dept = new Dept();
        dept.setDeptno(id);
        dept.setDname("无法找到id"+id);
        dept.setDb_source("no data in database");
        return dept;
    }

}
