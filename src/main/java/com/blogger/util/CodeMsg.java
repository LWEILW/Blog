package com.blogger.util;


public class CodeMsg implements Cloneable {
//    // 参考返回值定义
//    // 成功状态码
//    public static final int SUCCESS = 1;
//
//    // -------------------失败状态码----------------------
//    // 参数错误
//    public static final int PARAMS_IS_NULL = 10001;// 参数为空
//    public static final int PARAMS_NOT_COMPLETE = 10002; // 参数不全
//    public static final int PARAMS_TYPE_ERROR = 1003; // 参数类型错误
//    public static final int PARAMS_IS_INVALID = 10004; // 参数无效
//
//    // 用户错误
//    public static final int USER_NOT_EXIST = 20001; // 用户不存在
//    public static final int USER_NOT_LOGGED_IN = 20002; // 用户未登陆
//    public static final int USER_ACCOUNT_ERROR = 20003; // 用户名或密码错误
//    public static final int USER_ACCOUNT_FORBIDDEN = 20004; // 用户账户已被禁用
//    public static final int USER_HAS_EXIST = 20005;// 用户已存在
//
//    // 业务错误
//    public static final int BUSINESS_ERROR = 30001;// 系统业务出现问题
//
//    // 系统错误
//    public static final int SYSTEM_INNER_ERROR = 40001; // 系统内部错误
//
//    // 数据错误
//    public static final int DATA_NOT_FOUND = 50001; // 数据未找到
//    public static final int DATA_IS_WRONG = 50002;// 数据有误
//    public static final int DATA_ALREADY_EXISTED = 50003;// 数据已存在
//
//    // 接口错误
//    public static final int INTERFACE_INNER_INVOKE_ERROR = 60001; // 系统内部接口调用异常
//    public static final int INTERFACE_OUTER_INVOKE_ERROR = 60002;// 系统外部接口调用异常
//    public static final int INTERFACE_FORBIDDEN = 60003;// 接口禁止访问
//    public static final int INTERFACE_ADDRESS_INVALID = 60004;// 接口地址无效
//    public static final int INTERFACE_REQUEST_TIMEOUT = 60005;// 接口请求超时
//    public static final int INTERFACE_EXCEED_LOAD = 60006;// 接口负载过高
//
//    // 权限错误
//    public static final int PERMISSION_NO_ACCESS = 70001;// 没有访问权限



    private int retCode;
    private String message;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg EMPTY_PARAM_ERROR = new CodeMsg(400, "参数为空");
    public final static CodeMsg INTER_ERROR = new CodeMsg(505, "服务端异常");

    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (CodeMsg) super.clone();
    }
}

