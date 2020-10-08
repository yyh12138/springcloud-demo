package com.springcloud.service;

import com.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HystrixDeptClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public FeignDeptClientService create(Throwable throwable) {
        return new FeignDeptClientService() {
            @Override
            public Dept queryById(Long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDb_source("no data in database");
                dept.setDname("因流量激增当前服务已被关闭，资源提供主服务稳定");
                return dept;
            }

            @Override
            public List<Dept> queryAll() {
                Dept dept = new Dept();
                dept.setDeptno(0L);
                dept.setDb_source("no data in database");
                dept.setDname("这个服务已被关闭");
                List<Dept> depts = new ArrayList<>();
                depts.add(dept);
                return depts;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
