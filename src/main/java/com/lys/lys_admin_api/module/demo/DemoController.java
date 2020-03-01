package com.lys.lys_admin_api.module.demo;


import com.lys.lys_admin_api.common.exception.GlobalException;
import com.lys.lys_admin_api.module.demo.sys.mapper.SysUserMapper;
import com.lys.lys_admin_api.module.demo.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/demo/")
public class DemoController {

    @Autowired
    SysUserMapper sysUserMapper;

    @GetMapping(value="/test1")
    public Object test1(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("userId",123);
        map.put("wechat","lys20191020");
        map.put("comment","返利机器人");
       // testException();
        SysUser sysUser=sysUserMapper.selectByPrimaryKey("1");
        return sysUser;
    }

    private void testException(){
        throw new GlobalException("this is testException！");
    }
}
