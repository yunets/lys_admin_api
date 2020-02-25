package com.lys.lys_admin_api.module.demo;


import com.lys.lys_admin_api.common.exception.GlobalException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/demo/")
public class DemoController {

    @GetMapping(value="/test1")
    public Object test1(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("userId",123);
        map.put("wechat","lys20191020");
        map.put("comment","返利机器人");
        testException();
        return map;
    }

    private void testException(){
        throw new GlobalException("this is testException！");
    }
}
