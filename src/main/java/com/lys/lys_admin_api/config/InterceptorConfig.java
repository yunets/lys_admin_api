package com.lys.lys_admin_api.config;/**
 * Created by lys on 2019/4/15.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName InterceptorConfig
 * @Description 加入各种拦截器
 * @Author liuysh
 * @Date 2019/4/15 17:08
 **/
@Configuration

public class InterceptorConfig extends WebMvcConfigurerAdapter {
   // LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        // registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
      // registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/login/*").excludePathPatterns("/static/*");
    }


}
