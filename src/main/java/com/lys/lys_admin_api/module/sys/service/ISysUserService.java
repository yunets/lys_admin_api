package com.lys.lys_admin_api.module.sys.service;

import com.lys.lys_admin_api.module.sys.model.dto.SysUserDTO;

/**
 * @Auther: liuysh
 * @Date: 2020/7/10 17:13
 * @Description:
 */
public interface ISysUserService {
    Object users(SysUserDTO sysUserDTO);
}
