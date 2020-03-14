package com.springcloud.alibaba.service.impl;

import com.springcloud.alibaba.dao.OrderDao;
import com.springcloud.alibaba.domain.Order;
import com.springcloud.alibaba.service.AccountService;
import com.springcloud.alibaba.service.OrderService;
import com.springcloud.alibaba.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderDao orderDao;
    @Resource
    AccountService accountService;
    @Resource
    StorageService storageService;


    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说:
     * 下订单->减库存->减余额->改状态
     * GlobalTransactional seata开启分布式事务,异常时回滚,name保证唯一即可
     *
     * @param order 订单对象
     */
    @Override
    public void Create(Order order) {
        // 1 新建订单
        System.out.println("----->开始新建订单");
        orderDao.create(order);

        // 2 扣减库存
        System.out.println("----->订单微服务开始调用库存,做扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());
        System.out.println("----->订单微服务开始调用库存,做扣减End");

        // 3 扣减账户
        System.out.println("----->订单微服务开始调用账户,做扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        System.out.println("----->订单微服务开始调用账户,做扣减End");

        // 4 修改订单状态,从0到1,1代表已完成
        System.out.println("----->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);

        System.out.println("----->下订单结束了,O(∩_∩)O哈哈~");
    }
}
