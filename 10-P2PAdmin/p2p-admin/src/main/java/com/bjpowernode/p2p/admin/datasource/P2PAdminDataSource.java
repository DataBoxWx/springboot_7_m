package com.bjpowernode.p2p.admin.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * p2padmin数据源
 * 
 * @author yanglijun
 *
 */
@Configuration
@MapperScan(basePackages={"com.bjpowernode.p2p.admin.mapper"}, sqlSessionTemplateRef="sqlSessionTemplateForP2PAdmin")
public class P2PAdminDataSource {
	
	@Autowired
	private P2PAdminDataSourceConfig p2PAdminDataSourceConfig;

    @Bean("p2padminDataSource")
    public DruidDataSource p2padminDataSource () {
    	DruidDataSource druidDataSource = new DruidDataSource ();
    	druidDataSource.setUrl(p2PAdminDataSourceConfig.getUrl());
    	druidDataSource.setUsername(p2PAdminDataSourceConfig.getUsername());
    	druidDataSource.setPassword(p2PAdminDataSourceConfig.getPassword());
    	return druidDataSource;
    }
    
    @Bean("sqlSessionFactoryForP2PAdmin")
    public SqlSessionFactory sqlSessionFactoryForP2PAdmin () throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(this.p2padminDataSource()); //使用p2padmin数据源，连接p2padmin库
        return factoryBean.getObject();
    }

    @Bean("sqlSessionTemplateForP2PAdmin")
    public SqlSessionTemplate sqlSessionTemplateForP2PAdmin () throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryForP2PAdmin()); //使用上面配置的Factory
        return template;
    }
}