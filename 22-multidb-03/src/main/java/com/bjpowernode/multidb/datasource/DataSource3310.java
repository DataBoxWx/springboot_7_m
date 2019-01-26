package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:DataSource3310
 * package:com.bjpowernode.multidb.datasource
 * Descrption:
 *
 * @Date:2018/8/7 14:55
 * @Author:724班
 */
@Configuration("myDataSource3310")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3310", sqlSessionTemplateRef = "sqlSessionTemplate3310")
public class DataSource3310 {

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
     <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     <bean id="sqlSessionFactory3310" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource3310" />
     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3310() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3310());
        return sqlSessionFactoryBean;
    }

    /**
     <!-- scan for mappers and let them be autowired -->
     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
         <property name="basePackage" value="com.bjpowernode.multidb.mapper.db3310" />
         <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory3310"/>
     </bean>
     *
     */
    //springboot对这个配置有所变化，无法直接使用@Bean注解实现
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3310() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3310().getObject());
        return sqlSessionTemplate;
    }
}
