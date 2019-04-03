package com.musicbar.core.shiro;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.musicbar.second.comm.shiro.MyShiroRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfiguration {

    //将自己的验证方式加入容器
   /* @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }*/
    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(@Autowired AuthorizingRealm myShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }
    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager
    		,@Autowired FilterChainDefinition myShiroRealm) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
       
      
        shiroFilterFactoryBean.setFilterChainDefinitionMap(myShiroRealm.getFilterChainDefinitionMap());
        //登录
        shiroFilterFactoryBean.setLoginUrl(myShiroRealm.getUrlMap().get("loginUrl"));
        //首页
        shiroFilterFactoryBean.setSuccessUrl(myShiroRealm.getUrlMap().get("successUrl"));
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl(myShiroRealm.getUrlMap().get("unauthorizedUrl"));
        return shiroFilterFactoryBean;
    }
    
    //使后台controller类中的@RequiresPermissions注解生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
    @Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}
    //thymeleaf与shiro整合(前端标签权限验证用)
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
