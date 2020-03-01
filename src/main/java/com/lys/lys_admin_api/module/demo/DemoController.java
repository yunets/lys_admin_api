package com.lys.lys_admin_api.module.demo;


import com.github.pagehelper.PageHelper;
import com.lys.lys_admin_api.common.exception.GlobalException;
import com.lys.lys_admin_api.common.utils.PageInfoUtil;
import com.lys.lys_admin_api.module.demo.sys.mapper.SysUserMapper;
import com.lys.lys_admin_api.module.demo.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
        PageHelper.startPage(1, 10);
        SysUser sysUser=sysUserMapper.selectByPrimaryKey("1");
        return sysUser;
    }



    @GetMapping(value="/test2")
    public Object test11(){
        for(int i=0;i<20;i++){
            SysUser sysUser=new SysUser();
            sysUser.setId(UUID.randomUUID().toString());
            sysUser.setUsername("setUsername"+i);
          //  sysUserMapper.insert(sysUser);
        }




        PageHelper.startPage(1, 10);
        List<SysUser> list=sysUserMapper.selectAll();

        return PageInfoUtil.getPage(list);
    }

    private void testException(){
        throw new GlobalException("this is testException！");
    }
}
