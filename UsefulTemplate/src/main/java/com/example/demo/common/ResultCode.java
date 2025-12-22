// 状态码枚举
package com.example.demo.common;

import lombok.Getter;

@Getter
public enum ResultCode {

    // 通用状态码
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),

    // 参数相关 4xx
    PARAM_ERROR(400, "参数错误"),
    PARAM_IS_NULL(401, "参数为空"),
    PARAM_TYPE_ERROR(402, "参数类型错误"),
    PARAM_NOT_VALID(403, "参数校验失败"),

    // 认证授权相关 401-403
    UNAUTHORIZED(401, "未授权,请先登录"),
    TOKEN_INVALID(4011, "Token无效或已过期"),
    TOKEN_EXPIRED(4012, "Token已过期"),
    FORBIDDEN(403, "没有权限访问"),

    // 用户相关 1xxx
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_ALREADY_EXIST(1002, "用户已存在"),
    USERNAME_OR_PASSWORD_ERROR(1003, "用户名或密码错误"),
    USER_ACCOUNT_LOCKED(1004, "账号已被锁定"),
    USER_ACCOUNT_DISABLED(1005, "账号已被禁用"),

    // 业务相关 2xxx
    BUSINESS_ERROR(2000, "业务处理失败"),

    // 系统相关 5xxx
    SYSTEM_ERROR(5000, "系统异常"),
    DATABASE_ERROR(5001, "数据库异常"),
    NETWORK_ERROR(5002, "网络异常");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}