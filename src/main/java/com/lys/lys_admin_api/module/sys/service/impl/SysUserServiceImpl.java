package com.lys.lys_admin_api.module.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.lys.lys_admin_api.common.utils.PageInfoUtil;
import com.lys.lys_admin_api.module.sys.mapper.SysUserMapper;
import com.lys.lys_admin_api.module.sys.model.SysUser;
import com.lys.lys_admin_api.module.sys.model.dto.SysUserDTO;
import com.lys.lys_admin_api.module.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuysh
 * @Date: 2020/7/10 17:15
 * @Description:
 */

@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public Object users(SysUserDTO sysUserDTO) {
        PageHelper.startPage(sysUserDTO.getCurrentPage(), sysUserDTO.getPageSize());
        List<SysUser> list=sysUserMapper.selectAll(sysUserDTO);
        return PageInfoUtil.getPage(list);
    }
}
