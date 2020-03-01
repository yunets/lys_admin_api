package com.lys.lys_admin_api.common.bean;

import lombok.Data;

@Data
public class BasePageBean {
    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    protected int currentPage = DEFAULT_PAGE_NUM;
    protected int pageSize = DEFAULT_PAGE_SIZE;

    protected String order;
    protected String orderColumn;

}
