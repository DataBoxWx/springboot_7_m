package com.bjpowernode.activemq.type;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.activemq.model.Person;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

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
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        //设置包的信任
        List<String> list = new ArrayList<String>();
        list.add("com.bjpowernode.activemq.model");

        connectionFactory.setTrustedPackages(list);

        //2、创建连接
        try {
            connection = connectionFactory.createConnection();

            //3、创建session JMS1.1规范
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //4、创建目的地
            Destination destination = session.createQueue(DESTINATION_NAME);

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

                    //解析json的数据
                    JSONObject jsonObject = JSONObject.parseObject(text);
                    String name = jsonObject.getString("name");
                    String phone = jsonObject.getString("phone");
                    System.out.println("接收到的消息是：" + name + "---" + phone);

                    //直接把json字符串转出java对象
                    Person person = JSONObject.parseObject(text, Person.class);
                    System.out.println("接收到的消息为：" + person.getName() + "--" + person.getPhone());

                } else if (message instanceof ObjectMessage) {
                    Person person = (Person)((ObjectMessage) message).getObject();
                    System.out.println("接收到的消息为：" + person.getName() + "--" + person.getPhone());

                } else if (message instanceof MapMessage) {
                    boolean booleanValue = ((MapMessage) message).getBoolean("booleanKey");
                    int intValue = ((MapMessage) message).getInt("intKey");
                    String strValue = ((MapMessage) message).getString("strKey");
                    System.out.println(booleanValue + "---" + intValue + "---" + strValue);

                } else if (message instanceof BytesMessage) { //读的顺序和写的顺序要一致，否则会抛出异常
                    boolean booleanValue = ((BytesMessage) message).readBoolean();
                    double doubleValue = ((BytesMessage) message).readDouble();
                    String strValue = ((BytesMessage) message).readUTF();

                    System.out.println(booleanValue + "---" + doubleValue + "---" + strValue);

                } else if (message instanceof StreamMessage) { //读的顺序和写的顺序要一致，否则会抛出异常
                    boolean booleanValue = ((StreamMessage) message).readBoolean();
                    double doubleValue = ((StreamMessage) message).readDouble();
                    String strValue = ((StreamMessage) message).readString();

                    System.out.println(booleanValue + "---" + doubleValue + "---" + strValue);
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}