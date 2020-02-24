package com.lys.lys_admin_api.module.demo;


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
        map.put("userName","lys20191020");
        return map;
    }
}
