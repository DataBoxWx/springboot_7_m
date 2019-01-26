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
 * p2p数据源
 * 
 * @author yanglijun
 *
 */
@Configuration
@MapperScan(basePackages={"com.bjpowernode.p2p.loan.mapper"}, sqlSessionTemplateRef="sqlSessionTemplateForP2P")
public class P2PDataSource {
	
	@Autowired
	private P2PDataSourceConfig p2PDataSourceConfig;

	@Bean("p2pDataSource")
	public DruidDataSource p2pDataSource () {
    	DruidDataSource druidDataSource = new DruidDataSource ();
    	druidDataSource.setUrl(p2PDataSourceConfig.getUrl());
    	druidDataSource.setUsername(p2PDataSourceConfig.getUsername());
    	druidDataSource.setPassword(p2PDataSourceConfig.getPassword());
    	return druidDataSource;
    }
    
    @Bean("sqlSessionFactoryForP2P")
    public SqlSessionFactory sqlSessionFactoryForP2P () throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(this.p2pDataSource()); //使用p2padmin数据源，连接p2padmin库
        return factoryBean.getObject();
    }
    
    @Bean("sqlSessionTemplateForP2P")
    public SqlSessionTemplate sqlSessionTemplateForP2P () throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryForP2P()); //使用上面配置的Factory
        return template;
    }
}