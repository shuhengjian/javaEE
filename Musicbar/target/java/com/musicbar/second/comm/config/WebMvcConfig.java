package com.musicbar.second.comm.config;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.musicbar.second.comm.interceptor.LoginInterceptor;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	ConfigProperties config;
	@Autowired
    private LoginInterceptor loginInterceptor;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if(!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations("classpath:/template/");
		}
		if(!registry.hasMappingForPattern(config.getAccessPath())) {
			registry.addResourceHandler(config.getAccessPath()).addResourceLocations(config.getPath());
		}
	}

	/*@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new ("拦截器类")).addPathPatterns("/**");
	}*/
	
	/*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/front/**", "/backstage/lib/**", "/backstage/static/**", "/backstage/login.html");
    }*/

	@Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

}
