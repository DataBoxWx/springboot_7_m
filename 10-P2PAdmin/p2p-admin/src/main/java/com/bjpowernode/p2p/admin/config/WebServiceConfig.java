package com.bjpowernode.p2p.admin.config;

import com.bjpowernode.p2p.admin.stub.SealServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * WebService配置类
 *
 * @author yanglijun
 *
 */
//@Configuration
public class WebServiceConfig {

	@Bean
	public JaxWsPortProxyFactoryBean sealServiceBean() {
		try {
			JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
			proxy.setWsdlDocumentUrl(new URL("http://localhost:6868/services/sealService?wsdl"));
			proxy.setServiceName("sealService");
			proxy.setPortName("sealServicePort");
			proxy.setNamespaceUri("http://service.webservice.seal.bjpowernode.com/");
			proxy.setServiceInterface(SealServiceImpl.class);
			return proxy;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}