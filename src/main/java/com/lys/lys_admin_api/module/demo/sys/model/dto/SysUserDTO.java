package com.lys.lys_admin_api.module.demo.sys.model.dto;

import com.lys.lys_admin_api.common.bean.BasePageBean;
import lombok.Data;

import java.util.Date;

@Data
public class SysUserDTO extends  BasePageBean {
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
