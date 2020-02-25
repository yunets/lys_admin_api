package com.lys.lys_admin_api.common.exception;

import com.lys.lys_admin_api.common.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 * @author liuyunshengsir
 * @date 2019-08-02 23:10:24
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseVO handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseVO.error("路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseVO handlerHttpMessageNotReadableException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseVO.error("数据格式错误，请检查");
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO handleMyException(GlobalException e) {
        log.error(e.getMessage(), e);

        return ResponseVO.error(e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVO handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseVO.error("未知异常，请联系管理员");
    }

}
