package com.lys.lys_admin_api.config.security;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.pac4j.core.config.Config;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${cas.server.url}")
    private String casServerUrl;

    @Value("${cas.project.url}")
    private String projectUrl;

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(Pac4jSubjectFactory subjectFactory, CasRealm casRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(casRealm);
        manager.setSubjectFactory(subjectFactory);
        // Session托管给Spring-Session
        return manager;
    }

    /**
     * 使用 pac4j 的 subjectFactory
     *
     * @return
     */
    @Bean
    public Pac4jSubjectFactory subjectFactory() {
        return new Pac4jSubjectFactory();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
        return filterRegistration;
    }

    /**
     * 加载shiroFilter权限控制规则
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/callback", "callbackFilter");
        filterChainDefinitionMap.put("/logout", "logoutFilter");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/**/*.png", "anon");
        filterChainDefinitionMap.put("/**/*.svg", "anon");
        filterChainDefinitionMap.put("/**/*.js", "anon");
        filterChainDefinitionMap.put("/**/*.css", "anon");
//        filterChainDefinitionMap.put("/**/*.html", "anon");
        filterChainDefinitionMap.put("/**/*.json", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        //  TODO  勿删    生产环境启用
      // filterChainDefinitionMap.put("/**", "securityFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * shiroFilter
     *
     * @param securityManager
     * @param config
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager, Config config) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // 添加casFilter到shiroFilter中
        loadShiroFilterChain(shiroFilterFactoryBean);
        Map<String, Filter> filters = new HashMap<>(3);

        //cas 资源认证拦截器
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setConfig(config);
        filters.put("securityFilter", securityFilter);

        //cas 认证后回调拦截器
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(config);
        callbackFilter.setDefaultUrl(projectUrl);
        filters.put("callbackFilter", callbackFilter);

        // 注销 拦截器
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(config);
        logoutFilter.setCentralLogout(true);
        logoutFilter.setLocalLogout(true);
        logoutFilter.setDefaultUrl(projectUrl + "/callback");
        filters.put("logoutFilter", logoutFilter);

        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
        DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("singleSignOutFilter");
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casServerUrl);
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        bean.setFilter(singleSignOutFilter);
        bean.addUrlPatterns("/*");
        bean.setEnabled(true);
        return bean;
    }
}