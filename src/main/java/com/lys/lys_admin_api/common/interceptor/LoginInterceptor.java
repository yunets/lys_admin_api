package com.lys.lys_admin_api.common.interceptor;/**
 * Created by lys on 2019/4/15.
 */

import com.lys.lys_admin_api.common.bean.GlobalSessionConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author liuysh
 * @Date 2019/4/15 16:56
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session=httpServletRequest.getSession();
        if(session.getAttribute(GlobalSessionConstants.USER_SESSION)==null){
           String a=httpServletRequest.getContextPath();
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login/jsp?jsp=/login/login");
            return false;

        }
        return true ;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
