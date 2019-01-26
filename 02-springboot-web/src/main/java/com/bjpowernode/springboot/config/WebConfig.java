package com.bjpowernode.springboot.config;

import com.bjpowernode.springboot.filter.HeFilter;
import com.bjpowernode.springboot.interceptor.LoginInterceptor;
import com.bjpowernode.springboot.servlet.HeServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:WebConfig
 * package:com.bjpowernode.springboot.config
 * Descrption:
 *
 * @Date:2018/7/24 14:36
 * @Author:724班
 */
@Configuration // @Configuration 相当于是一个 applicationContext-mvc.xml
public class WebConfig implements WebMvcConfigurer {

    /**
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截器需要拦截的路径
        String[] pathPatterns = {
            "/**"
        };

        //拦截器不需要拦截的路径
        String[] excludePathPatterns = {
                "/boot/hello",
                "/boot/config"
        };

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(pathPatterns).excludePathPatterns(excludePathPatterns);
    }

    /**
     * @Bean 相当于xml里面的 <bean id="heServletRegistrationBean" class="xxx.xx.ServletRegistrationBean"></bean>
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean heServletRegistrationBean(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new HeServlet(), "/heServlet");
        return registration;
    }

    /**
     * 注册我们自定义的一个过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean heFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new HeFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * 注册一个Spring提供的字符编码过滤器
     *
     * @return
     */
    /*@Bean
    public FilterRegistrationBean filterRegistrationBean() {

        //创建一个spring提供的Filter过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);//强制编码
        characterEncodingFilter.setEncoding("UTF-8");//编码类型UTF-8

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(characterEncodingFilter);
        registrationBean.addUrlPatterns("/*");//要过滤的路径是所有的路径
        return registrationBean;
    }*/
}