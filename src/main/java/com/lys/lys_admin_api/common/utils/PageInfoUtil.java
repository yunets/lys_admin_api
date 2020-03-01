package com.lys.lys_admin_api.common.utils;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public class PageInfoUtil {
    public static Map getPage(List list){
        PageInfo<Map> pageInfo = new PageInfo(list);
        return ImmutableMap.builder().put("list", pageInfo.getList()).put("current", pageInfo.getPageNum())
                .put("pageSize", pageInfo.getPageSize()).put("total", pageInfo.getTotal()).build();

    }
}
