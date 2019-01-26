package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:DataSource3308
 * package:com.bjpowernode.multidb.datasource
 * Descrption:
 *
 * @Date:2018/8/7 14:55
 * @Author:724班
 */
@Configuration("myDataSource3308")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3308", sqlSessionTemplateRef = "sqlSessionTemplate3308")
public class DataSource3308 {

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

    /**
     <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     <bean id="sqlSessionFactory3308" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource3308" />
     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3308() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3308());
        return sqlSessionFactoryBean;
    }

    /**
     <!-- scan for mappers and let them be autowired -->
     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
         <property name="basePackage" value="com.bjpowernode.multidb.mapper.db3308" />
         <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory3308"/>
     </bean>
     *
     */
    //springboot对这个配置有所变化，无法直接使用@Bean注解实现
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3308() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3308().getObject());
        return sqlSessionTemplate;
    }
}
