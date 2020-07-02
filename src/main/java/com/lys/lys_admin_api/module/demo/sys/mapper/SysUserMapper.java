package com.lys.lys_admin_api.module.demo.sys.mapper;


import com.lys.lys_admin_api.module.demo.sys.model.SysUser;
import com.lys.lys_admin_api.module.demo.sys.model.dto.SysUserDTO;
import org.apache.ibatis.annotations.Select;
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
    @Select("SELECT  id " + " FROM  sys_user")
    List<SysUser> selectAll(SysUserDTO sysUsserDTO);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}