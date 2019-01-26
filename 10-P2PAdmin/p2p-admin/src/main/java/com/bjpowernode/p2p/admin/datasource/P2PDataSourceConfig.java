package com.bjpowernode.p2p.admin.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取p2p数据源的配置
 * 
 * @author yanglijun
 *
 */
@Component("p2PDataSourceConfig")
@ConfigurationProperties(prefix="spring.datasource.p2p")
public class P2PDataSourceConfig {

	private String url;
	
	private String username;
	
	private String password;
	
	private String driver;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
}