package com.lys.lys_admin_api.module.sys.mapper;


import com.lys.lys_admin_api.module.sys.model.SysUser;
import com.lys.lys_admin_api.module.sys.model.dto.SysUserDTO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@MapperScan
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);


    List<SysUser> selectAll(SysUserDTO sysUserDTO);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}