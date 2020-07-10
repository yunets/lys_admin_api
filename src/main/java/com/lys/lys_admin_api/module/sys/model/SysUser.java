package com.lys.lys_admin_api.module.sys.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private String id;

    private String username;

    private String loginname;

    private String password;

    private String email;

    private String mobile;

    private String statusFlag;

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;


}