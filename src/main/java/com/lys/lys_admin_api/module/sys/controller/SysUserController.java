package com.lys.lys_admin_api.module.sys.controller;

import com.lys.lys_admin_api.module.sys.model.dto.SysUserDTO;
import com.lys.lys_admin_api.module.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: liuysh
 * @Date: 2020/7/10 17:21
 * @Description:
 */
@Api(tags = "用户")
@RestController
@Slf4j
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;


    @ApiOperation("用户分页")
    @PostMapping("/selectAll")
    public Object selectAll(@RequestBody SysUserDTO sysUserDTO)
    {
        return sysUserService.users(sysUserDTO);
    }


}
