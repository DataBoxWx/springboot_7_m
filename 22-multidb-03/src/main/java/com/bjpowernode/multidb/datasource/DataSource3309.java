package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:DataSource3309
 * package:com.bjpowernode.multidb.datasource
 * Descrption:
 *
 * @Date:2018/8/7 14:55
 * @Author:724班
 */
@Configuration("myDataSource3309")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3309", sqlSessionTemplateRef = "sqlSessionTemplate3309")
public class DataSource3309 {

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

    /**
     <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     <bean id="sqlSessionFactory3309" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource3309" />
     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3309() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3309());
        return sqlSessionFactoryBean;
    }

    /**
     <!-- scan for mappers and let them be autowired -->
     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
         <property name="basePackage" value="com.bjpowernode.multidb.mapper.db3309" />
         <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory3309"/>
     </bean>
     *
     */
    //springboot对这个配置有所变化，无法直接使用@Bean注解实现
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3309() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3309().getObject());
        return sqlSessionTemplate;
    }
}
