package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:DataSource3307
 * package:com.bjpowernode.multidb.datasource
 * Descrption:
 *
 * @Date:2018/8/7 14:55
 * @Author:724班
 */
@Configuration("myDataSource3307")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3307", sqlSessionTemplateRef = "sqlSessionTemplate3307")
public class DataSource3307 {

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

    /**
     <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     <bean id="sqlSessionFactory3307" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource3307" />
     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3307() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3307());
        return sqlSessionFactoryBean;
    }

    /**
     <!-- scan for mappers and let them be autowired -->
     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
         <property name="basePackage" value="com.bjpowernode.multidb.mapper.db3307" />
         <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory3307"/>
     </bean>
     *
     */
    //springboot对这个配置有所变化，无法直接使用@Bean注解实现
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3307() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3307().getObject());
        return sqlSessionTemplate;
    }
}
