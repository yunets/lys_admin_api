package com.lys.lys_admin_api.config;



import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DruidConfig
 * @Description TODO
 * @Author liuysh
 * @Date 2019/4/8 20:43
 **/

@SuppressWarnings("all")
@Configuration
public class DruidConfig {
    @Bean
    public ServletRegistrationBean statViewServletDemo() {
        ServletRegistrationBean srb = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
        srb.addInitParameter("allow", "*");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        //srb.addInitParameter("deny", "192.168.25.123");
        // 添加控制台管理用户
        srb.addInitParameter("loginUsername", "admin");
        srb.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据
        srb.addInitParameter("resetEnable", "false");
        return srb;
    }
    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilterDemo() {
        FilterRegistrationBean frb = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        frb.addUrlPatterns("/*");
        // 忽略过滤格式
        frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return frb;
    }


}
