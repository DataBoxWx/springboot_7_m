package com.bjpowernode.activemq.type;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.activemq.model.Person;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点对点消息发送 1:1模式
 *
 * package:com.bjpowernode.activemq.queue
 * Descrption:
 *
 * @Date:2018/8/2 17:34
 * @Author:724班
 */
public class QueueSender {

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

        MessageProducer messageProducer = null;

        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        //2、创建连接
        try {
            connection = connectionFactory.createConnection();

            //3、创建session JMS1.1规范
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //4、创建消息
            //Message message = session.createTextMessage("文本消息：Hello ActiveMQ.");
            Person person = new Person();
            person.setName("张三丰");
            person.setPhone("13700000000");

            //创建一个对象消息
            //Message message = session.createObjectMessage(person);

            //把对象转出json格式数据
            String personJSON = JSONObject.toJSONString(person);

            Message message = session.createTextMessage(personJSON);

            /*MapMessage message = session.createMapMessage();
            message.setBoolean("booleanKey", true);
            message.setInt("intKey", 199);
            message.setString("strKey", "蛙课网"); //java 爪哇*/

            /*BytesMessage message = session.createBytesMessage();
            message.writeBoolean(true);
            message.writeDouble(199.9);
            message.writeUTF("蛙课网");*/

            /*StreamMessage message = session.createStreamMessage();
            message.writeBoolean(true);
            message.writeDouble(199.9);
            message.writeString("蛙课网");*/

            //5、创建目的地
            Destination destination = session.createQueue(DESTINATION_NAME);

            //6、创建消息生产者
            messageProducer = session.createProducer(destination);

            //7、发送消息
            messageProducer.send(message);

            System.out.println("消息发送完毕.");

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != messageProducer) {
                    messageProducer.close();
                }
                if (null != session) {
                    session.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
