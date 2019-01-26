package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:DataSource
 * package:com.bjpowernode.multidb.datasource
 * Descrption:
 *
 * @Date:2018/8/7 15:25
 * @Author:724班
 */
@Configuration
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper")
public class DataSource {

    @Value("${spring.datasource.username3307}")
    private String userName3307;

    @Value("${spring.datasource.password3307}")
    private String password3307;

    @Value("${spring.datasource.driver3307}")
    private String driver3307;

    @Value("${spring.datasource.url3307}")
    private String url3307;

    /**
     <!-- 配置数据源，阿里巴巴Druid数据库连接池 -->
     <bean id="dataSource3307" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
     <property name="url" value="jdbc:mysql://192.168.160.128:3307/test01?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false" />
     <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
     <property name="username" value="root" />
     <property name="password" value="123456" />
     </bean>
     */
    @Bean
    public DruidDataSource dataSource3307() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3307);
        druidDataSource.setDriverClassName(driver3307);
        druidDataSource.setUsername(userName3307);
        druidDataSource.setPassword(password3307);
        return druidDataSource;
    }

    @Value("${spring.datasource.username3308}")
    private String userName3308;

    @Value("${spring.datasource.password3308}")
    private String password3308;

    @Value("${spring.datasource.driver3308}")
    private String driver3308;

    @Value("${spring.datasource.url3308}")
    private String url3308;

    /**
     <!-- 配置数据源，阿里巴巴Druid数据库连接池 -->
     <bean id="dataSource3308" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
     <property name="url" value="jdbc:mysql://192.168.160.128:3308/test01?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false" />
     <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
     <property name="username" value="root" />
     <property name="password" value="123456" />
     </bean>
     */
    @Bean
    public DruidDataSource dataSource3308() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3308);
        druidDataSource.setDriverClassName(driver3308);
        druidDataSource.setUsername(userName3308);
        druidDataSource.setPassword(password3308);
        return druidDataSource;
    }

    @Value("${spring.datasource.username3309}")
    private String userName3309;

    @Value("${spring.datasource.password3309}")
    private String password3309;

    @Value("${spring.datasource.driver3309}")
    private String driver3309;

    @Value("${spring.datasource.url3309}")
    private String url3309;

    /**
     <!-- 配置数据源，阿里巴巴Druid数据库连接池 -->
     <bean id="dataSource3309" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
     <property name="url" value="jdbc:mysql://192.168.160.128:3309/test01?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false" />
     <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
     <property name="username" value="root" />
     <property name="password" value="123456" />
     </bean>
     */
    @Bean
    public DruidDataSource dataSource3309() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3309);
        druidDataSource.setDriverClassName(driver3309);
        druidDataSource.setUsername(userName3309);
        druidDataSource.setPassword(password3309);
        return druidDataSource;
    }

    @Value("${spring.datasource.username3310}")
    private String userName3310;

    @Value("${spring.datasource.password3310}")
    private String password3310;

    @Value("${spring.datasource.driver3310}")
    private String driver3310;

    @Value("${spring.datasource.url3310}")
    private String url3310;

    /**
     <!-- 配置数据源，阿里巴巴Druid数据库连接池 -->
     <bean id="dataSource3310" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
     <property name="url" value="jdbc:mysql://192.168.160.128:3310/test01?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false" />
     <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
     <property name="username" value="root" />
     <property name="password" value="123456" />
     </bean>
     */
    @Bean
    public DruidDataSource dataSource3310() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3310);
        druidDataSource.setDriverClassName(driver3310);
        druidDataSource.setUsername(userName3310);
        druidDataSource.setPassword(password3310);
        return druidDataSource;
    }

    /**
     <!--要实现一个动态数据源，动态数据源就是在程序运行的时候，决定采用什么数据源，配置文件中不要配死-->
     <bean id="dynamicDataSource" class="com.bjpowernode.multidb.datasource.DynamicDataSource">
         <property name="targetDataSources">
             <map>
                 <entry key="db3307" value-ref="dataSource3307"/>
                 <entry key="db3308" value-ref="dataSource3308"/>
                 <entry key="db3309" value-ref="dataSource3309"/>
                 <entry key="db3310" value-ref="dataSource3310"/>
             </map>
         </property>
     </bean>
     *
     */
    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap();
        targetDataSources.put(DynamicDataSource.DB3307, dataSource3307());
        targetDataSources.put(DynamicDataSource.DB3308, dataSource3308());
        targetDataSources.put(DynamicDataSource.DB3309, dataSource3309());
        targetDataSources.put(DynamicDataSource.DB3310, dataSource3310());

        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dynamicDataSource" />
     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());

        return sqlSessionFactoryBean;
    }

    /**
     <!-- scan for mappers and let them be autowired -->
     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
         <property name="basePackage" value="com.bjpowernode.multidb.mapper" />
         <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
     </bean>
     */
    //该配置的实现，在spring boot框架下，需要使用 SqlSessionTemplate + @MapperScan
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sqlSessionTemplate;
    }

}
