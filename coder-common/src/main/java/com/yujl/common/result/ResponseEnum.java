package com.yujl.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
/*
 *@Author 19145
 *@Description
 *@Date 19:45 2021/11/28
 *@Param
 *@return
 **/
public enum ResponseEnum {
    //成功
    SUCCESS(0, "成功"),
    //失败
    ERROR(-1, "失败"),

    BAD_SQL_GRAMMAR_ERROR(-1, "数据持久层发生错误"),

    BORROW_AMOUNT_NULL_ERROR(-201, "借款额度不能为空"),

    EXPORT_DATA_ERROR(104, "数据导出失败"),

    DATA_NOT_NULL_ERROR(-202, "数据不存在"),

    UPLOAD_ERROR(-203, "文件解析失败"),

    SERVLET_ERROR(-102, "servlet请求异常"),

    MOBILE_NULL_ERROR(-202, "手机号不能为空"),

    MOBILE_ERROR(-203, "手机号不正确"),

    PASSWORD_NULL_ERROR(-204, "密码不能为空"),

    CODE_NULL_ERROR(-205, "验证码不能为空"),

    CODE_ERROR(-206, "验证码不正确"),

    MOBILE_EXIST_ERROR(-207, "手机号已被注册"),

    LOGIN_MOBILE_ERROR(-208, "用户不存在"),

    LOGIN_PASSWORD_ERROR(-209, "密码不正确"),

    LOGIN_DISABLED_ERROR(-210, "用户已被禁用"),

    LOGIN_AUTH_ERROR(50008, "用户未登录"),

    CODE_REPEAT_SEND(212, "重复发送验证码"),

    FIELD_NULL_REEOR(212, "字段不能为空"),

    ALIYUN_RESPONSE_FAIL(-501, "阿里云响应失败"),

    ALIYUN_SMS_LIMIT_CONTROL_ERROR(-502, "短信发送过于频繁"),//业务限流

    ALIYUN_SMS_ERROR(-503, "短信发送失败"),//其他失败
    ;
    //状态码
    private Integer code;
    //信息
    private String message;
}
