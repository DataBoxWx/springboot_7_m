package com.bjpowernode.seckill.activemq;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.seckill.model.Orders;
import com.bjpowernode.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * ClassName:ListenerService
 * package:com.bjpowernode.activemq.listener
 * Descrption:
 *
 * @Date:2018/8/4 14:57
 * @Author:724班
 */
@Component //就是spring容器中的一个bean
public class ListenerService {

    @Autowired
    private GoodsService goodsService;

    /**
     * 接收消息的方法
     *
     * 生产者发送消息太多或者太快，会导致消息的堆积，不能及时处理，此时可以增加消费者个数来解决该问题
     *
     */
    @JmsListener(destination="${spring.jms.template.default-destination}", concurrency="16")
    public void receiveMessage(Message message) {
        //判断一下是不是文本消息
        if (message instanceof TextMessage) {

            Orders orders = null;

            String orderJSON = null;
            try {
                orderJSON = ((TextMessage) message).getText();

                orders = JSONObject.parseObject(orderJSON, Orders.class);

                //接收到消息后，给用户下订单
                goodsService.createOrders(orders);

            } catch (JMSException e) {
                e.printStackTrace();
                System.out.println("JMS接收消息的异常...........");

                //处理异常（下单异常）
                goodsService.processException(orders);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据库下订单方法的异常...........");

                //处理异常（下单异常）
                goodsService.processException(orders);

            }
            System.out.println("接收到的消息是：" + orderJSON);
        }
    }
}
