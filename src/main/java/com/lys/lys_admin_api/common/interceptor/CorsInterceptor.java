package com.lys.lys_admin_api.common.interceptor;/**
 * Created by lys on 2019/5/11.
 */

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CorsInterceptor
 * @Description 解决跨域
 * @Author liuysh
 * @Date 2019/5/11 15:54
 **/
public class CorsInterceptor implements HandlerInterceptor {



    private static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
    private static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
    private static final String METHODS_NAME = "Access-Control-Allow-Methods";
    private static final String HEADERS_NAME = "Access-Control-Allow-Headers";
    private static final String MAX_AGE_NAME = "Access-Control-Max-Age";




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        response.setHeader(CREDENTIALS_NAME, "true");
        //谁请求就把谁加入白名单，即任何客户端都可跨域，为了方便开发
        response.setHeader(ORIGIN_NAME, origin);
        response.setHeader(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE");
        response.setHeader(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept,access-token");
        response.setHeader(MAX_AGE_NAME, "3600");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
