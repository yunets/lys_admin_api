package com.lys.lys_admin_api.common.exception;

/**
 * 全局异常处理类
 *
 * @author liuyunshengsir
 * @date 2019-08-02 23:10:24
 */
public class GlobalException extends RuntimeException {
    private static final int ERROR_CODE = 500;

    private final int code;
    private final String msg;

    public GlobalException(String msg) {
        super(msg);
        this.code = ERROR_CODE;
        this.msg = msg;
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
        this.code = ERROR_CODE;
        this.msg = msg;
    }

    public GlobalException(String msg, int code) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GlobalException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public static void throwException(String msg){
        throw new GlobalException(msg);
    }

    public static void throwException(Throwable e, String msg) {
        throw new GlobalException(msg, e);
    }
}
