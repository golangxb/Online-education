package com.guli.common.constants;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-24
 * Time : 14:13
 **/
@Getter
public enum  ResultCodeEnum {
    SUCCESS(true,20000,"成功"),
    UNKNOWN_REASON(false,20001,"未知错误"),
    PARAM_ERROR(false,21002,"参数异常"),
    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常");



    private boolean success;
    private Integer code;
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;

    }
}
