package com.lys.lys_admin_api.common.demon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @Auther: liuysh
 * @Date: 2020/8/19 09:10
 * @Description: 国际化
 */
@RestController
public class I18nController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("i18n")
    public String i18n() {
        // 获取当前请求的区域Locale
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("username", null, locale);
    }
}
