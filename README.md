#springcloud的学习
# Eureka服务注册的基础、单机、集群、actuator微服务信息完善（改主机名称用instance+本地修改）、服务发现discover和 Eureka的自我保护
# consul服务注册的配置和使用，zookeeper未学
# Ribbon负载均衡服务调用，基础+源码实现（cas+自寻锁）
# OpenFeign服务接口的使用、超时控制和日志打印功能
# 断路器 服务降级（可全局设置解决代码混乱和膨胀） 服务熔断（断路器：closed-open-halfopen） 服务限流  服务监控HystrixDashBoardHystrix
# Gateway服务网关（（由路由->断言->过滤器）（核心逻辑：路由转发+执行过滤链）  路由转发    网关路由的配置（在yml中配置或代码注入）  动态路由  ）  断言predicate(Route Predicate)  过滤器（gatewayFilter和自定义全局globalFilter）  