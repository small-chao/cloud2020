package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //将每个service都装到list中
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
