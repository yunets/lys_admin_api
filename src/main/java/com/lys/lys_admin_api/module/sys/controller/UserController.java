package com.lys.lys_admin_api.module.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.lys.lys_admin_api.module.sys.mapper.SysUserMapper;
import com.lys.lys_admin_api.module.sys.model.SysUser;
import com.lys.lys_admin_api.module.demo.sys.model.dto.SysUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "用户")
@RestController
@Slf4j
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    SysUserMapper sysUserMapper;

    @ApiOperation("用户分页")
    @PostMapping ("/selectAll")
    public Object selectAll(@RequestBody SysUserDTO sysUsserDTO)
    {
        PageHelper.startPage(sysUsserDTO.getCurrentPage(), sysUsserDTO.getPageSize());
        List<SysUser> list=sysUserMapper.selectAll(sysUsserDTO);
        PageInfo<Map> pageInfo = new PageInfo(list);
        return ImmutableMap.builder().put("list", pageInfo.getList()).put("current", pageInfo.getPageNum())
                .put("pageSize", pageInfo.getPageSize()).put("total", pageInfo.getTotal()).build();
       // return PageInfoUtil.getPage(list);
    }
}
