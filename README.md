#跟着阳哥的教程来敲的，springcloud的学习
# Eureka服务注册的基础、单机、集群、actuator微服务信息完善（改主机名称用instance+本地修改）、服务发现discover和 Eureka的自我保护
# consul服务注册的配置和使用，zookeeper未学
# Ribbon负载均衡服务调用，基础+源码实现（cas+自寻锁）
# OpenFeign服务接口的使用、超时控制和日志打印功能
# Hystrix断路器 服务降级（可全局设置解决代码混乱和膨胀） 服务熔断（断路器：closed-open-halfopen） 服务限流  服务监控HystrixDashBoardHystrix
# Gateway服务网关（（由路由->断言->过滤器）（核心逻辑：路由转发+执行过滤链）  路由转发    网关路由的配置（在yml中配置或代码注入）  动态路由  ）  断言predicate(Route Predicate)  过滤器（gatewayFilter和自定义全局globalFilter）  
# config服务配置（分布式配置中心） 服务端和客户端的配置 客户端的动态刷新
#Bus消息总线 （通过rabbitmq）  广播刷新全局  定点刷新通知 
# stream消息驱动(屏蔽型)底层消息中间件的养异（mq的差异）,降低切换成本，统一消息的编程模型）  (binder绑定器  channel频道 source和sink)    消费者和生产者的构建   分组消费与持久化
#Sleuth分布式请求链路跟踪  链路的监测（sleuth+zipkin）
#springcloud Alibaba:
#    Nacos(注册中心+配置中心)  基础配置（自带动态刷新）  三级分类目录(Namespace+Group+DataId)   Nacos集群和持久化配置
#    Sentinel熔断与限流    流控规则（流量）（快速失败、预热和排队等待）   降级规则（RT平均响应时间、异常比例和异常数）   热点规则（热点key限流）@SentinelResource     系统规则（系统自适应限流）(全局设置) 按资源名称限流+后续处理、按照UrI地址限流+后续处理、上面兜底方案面临的问题、客户自定义限流处理逻辑等等        服务熔断功能（Sentinel整合Ribbon+OpenFegin+fallback)   持久化规则