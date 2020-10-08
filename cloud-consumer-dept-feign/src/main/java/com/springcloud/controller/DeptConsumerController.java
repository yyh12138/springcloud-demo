package com.springcloud.controller;

import com.springcloud.pojo.Dept;
import com.springcloud.service.FeignDeptClientService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptConsumerController {

    @Resource
    private FeignDeptClientService feignDeptClientService;

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return feignDeptClientService.addDept(dept);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return feignDeptClientService.queryById(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return feignDeptClientService.queryAll();
    }

}
