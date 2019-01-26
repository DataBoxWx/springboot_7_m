package com.bjpowernode.activemq.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点对点消息接收者
 *
 * package:com.bjpowernode.activemq.queue
 * Descrption:
 *
 * @Date:2018/8/2 17:51
 * @Author:724班
 */
public class QueueReceiver {

    /**
     * 消息服务器的连接地址
     */
    public static final String BROKER_URL = "tcp://192.168.160.128:61616";

    /**
     * 消息目的地的名称
     */
    public static final String DESTINATION_NAME = "myQueue";

    public static void main(String[] args) {

        Connection connection = null;

        Session session = null;

        MessageConsumer messageConsumer = null;

        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        //2、创建连接
        try {
            connection = connectionFactory.createConnection();

            //3、创建session JMS1.1规范 接收消息，开启事务消息
            session = connection.createSession(Boolean.TRUE, Session.SESSION_TRANSACTED);

            //4、创建目的地
            Destination destination = session.createQueue(DESTINATION_NAME);

            //5、创建消息消费者
            messageConsumer = session.createConsumer(destination);

            //@TODO 在接收消息之前，需要把连接启动一下
            connection.start();

            //while (true) {
                //6、发送消息  receive()是阻塞方法，它会一直等，直到等到消息位置
                Message message = messageConsumer.receive();

                //判断一下是不是文本消息
                if (message instanceof TextMessage) {

                    String text = ((TextMessage) message).getText();
                    System.out.println("接收到的消息是：" + text);
                }
            //}

            //事务消息，对应接收者来说，需要手动提交事务，否则消息没有被真正地消费，会产生重复消费
            //消息队列，消息是不运行重复消费的，如果可能重复消费，你需要在代码中进行控制判断
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}