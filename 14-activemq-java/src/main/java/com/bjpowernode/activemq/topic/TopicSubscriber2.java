package com.bjpowernode.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布订阅模式的消息订阅者
 *
 * 发布订阅模式下，需要先订阅，再发布，不能颠倒顺序
 *
 * package:com.bjpowernode.activemq.topic
 * Descrption:
 *
 * @Date:2018/8/3 14:37
 * @Author:724班
 */
public class TopicSubscriber2 {

    /**
     * 消息服务器的连接地址
     */
    public static final String BROKER_URL = "tcp://192.168.160.128:61616";

    /**
     * 消息目的地的名称
     */
    public static final String DESTINATION_NAME = "myTopic";

    public static void main(String[] args) {

        Connection connection = null;

        Session session = null;

        MessageConsumer messageConsumer = null;

        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        //2、创建连接
        try {
            connection = connectionFactory.createConnection();

            //3、创建session JMS1.1规范
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //4、创建目的地
            Destination destination = session.createTopic(DESTINATION_NAME);

            //5、创建消息消费者
            messageConsumer = session.createConsumer(destination);

            //@TODO 在接收消息之前，需要把连接启动一下
            connection.start();

            while (true) {

                //6、发送消息  receive()是阻塞方法，它会一直等，直到等到消息位置
                Message message = messageConsumer.receive();

                //判断一下是不是文本消息
                if (message instanceof TextMessage) {

                    String text = ((TextMessage) message).getText();
                    System.out.println("接收到的消息是：" + text);
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
