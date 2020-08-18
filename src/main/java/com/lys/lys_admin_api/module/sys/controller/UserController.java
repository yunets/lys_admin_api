package com.lys.lys_admin_api.module.sys.controller;

import com.lys.lys_admin_api.common.ResponseVO;
import com.lys.lys_admin_api.module.sys.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: liuysh
 * @Date: 2020/7/31 17:26
 * @Description:
 */
@Api(tags = "用户")
@RestController
@Slf4j
@RequestMapping("/")
public class UserController {
    @ApiOperation("获取当前用户")
    @PostMapping("login")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆

        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }

    }

    @ApiOperation("获取当前用户")
    @GetMapping("api/currentUser")
    public Object currentUser()
    {
        Subject s=SecurityUtils.getSubject();
        SysUser user = (SysUser) s.getPrincipal();
        if(user==null){
            return ResponseVO.error("用户未登录");
        }
        return user;
    }


    /**
     * shiro注销，shiro会自动把session释放，所以不需要调用session.invalidate();方法
     */
    @ApiOperation("获取当前用户")
    @GetMapping("/logout")
    public void logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    @ApiOperation("获取当前用户")
    @GetMapping("notLogin")
    public Object notLogin()
    {
         return ResponseVO.error("用户未登录");
    }
}
