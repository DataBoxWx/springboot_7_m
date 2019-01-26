package com.bjpowernode.activemq.config;

import com.bjpowernode.activemq.listener.MyMessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * ClassName:ActiveMQConfig
 * package:com.bjpowernode.activemq.config
 * Descrption:
 *
 * @Date:2018/8/4 15:12
 * @Author:724班
 */
@Configuration //等价于一个spring的xml配置文件
public class ActiveMQConfig {

    @Autowired
    private ActiveMQConnectionFactory connectionFactory;

    @Autowired
    private MyMessageListener myMessageListener;

    @Value("${spring.jms.template.default-destination}")
    private String destination;

    @Value("${spring.jms.pub-sub-domain}")
    public boolean pubSubDomain;

    /**
     *
     1、
     <!-- 配置一个连接工厂 -->
     <bean id="connectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
        <property name="brokerURL" value="tcp://192.168.160.128:61616"/>
     </bean>

     2、
     <!-- 配置一个sping JMS 监听器的容器 -->
     <bean class="org.springframework.jms.listener.DefaultMessageListenerContainer">
         <property name="connectionFactory" ref="connectionFactory"/>
         <property name="destinationName" value="springTopic"/>
         <property name="messageListener" ref="receiverListener" />
         <!--发送模式，true表示发布订阅模式，false表示点对点模式，默认是false所以默认是点对点模式-->
         <property name="pubSubDomain" value="true"/>
     </bean>

     3、
     <!--自定义的消息监听器-->
     <bean id="receiverListener" class="com.bjpowernode.activemq.listener.MyMessageListener"/>
     *
     */

    //1、connectionFactory 这个bean，springboot已经自动配置了


    //2、DefaultMessageListenerContainer 这个bean，需要我们自己配置
    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer() {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestinationName(destination);
        defaultMessageListenerContainer.setMessageListener(myMessageListener);
        defaultMessageListenerContainer.setPubSubDomain(pubSubDomain);

        //在实际项目中，发送者发送消息太快了，导致发送了大量的消息，消费者都没有来得及消费，就会有大量消息堆积，就会导致业务处理的延迟
        //解决方案：增加消费者的数量，让消息消费得更快一些
        defaultMessageListenerContainer.setConcurrency("5");//并行有5个消费者去消费

        return defaultMessageListenerContainer;
    }

    //3、receiverListener 这个bean，我们是直接在类上加了注解实现类，所以不需要使用@Bean注解进行配置
}