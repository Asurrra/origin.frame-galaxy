package com.cyw.origin.frame.galaxy.common.enums;

/**
 * 通用返回枚举
 * @author yiwen.chang
 * @version 1.0.0
 * @date 2018/2/28
 */
public enum ReturnCode implements ErrorCode {

    SUCCESS("0", "success"),
    FAILED("1", "success"),
    UNKNOWNED_ERROR("1700100101000", "未知错误"),
    SERVICE_EXCEPTION("1700100101001", "服务异常"),
    /** 入参 */
    ARGS_ERROR("1700100101002", "参数错误"),
    ARGS_EMPTY("1700100101003", "必要参数为空"),
    OBJECT_DELETED("1700100101004", "对象不存在"),
    REFLECT_EXCEPTION("1700100101005", "反射方法调用异常"),
    /** 数据库 */
    DB_OPERATION_EXCEPTION("1700100101006", "数据库操作异常"),
    UNSUPPORTED_DATATYPE("1700100101007", "不支持的数据类型"),
    DATA_UNIQUE("1700100101008", "数据不唯一"),
    DB_SHARD_EXCEPTION("1700100101009", "分表异常"),
    VERSION_NOT_MATCH("1700100101010", "数据版本不匹配"),
    /** 业务方服务 */
    NOT_OPEN("1700100101014", "此功能没有开启"),
    AC_EXCEPTION("1700100101015", "AC接口调用异常"),
    BC_EXCEPTION("1700100101016", "BC接口调用异常"),
    /** 用户权限 */
    BASE_LOGIN_USER_NOT_LOGIN("1700100102000", "用户尚未登录"),
    BASE_LOGIN_USER_NOT_ALLOWD("1700100102001", "用户无运营系统访问权限"),
    BASE_LOGIN_INVALID_PASSWORD("1700100102002", "用户名或密码错误"),
    BASE_LOGIN_USER_LOGOUT_FAIL("1700100102003", "用户退出登录失败"),
    BASE_LOGIN_USER_ACCESS_FORBIDDEN("1700100102004", "用户无访问权限"),
    BASE_AUTH_USER_ACCESS_FAILED("1700100102005", "获取用户权限集失败"),
    /** 角色 */
    RBAC_ROLE_GET_EXCEPTION("1700100103001", "查询角色列表异常"),
    RBAC_ROLE_SAVE_EXCEPTION("1700100103002", "保存角色列表异常"),
    RBAC_RES_GET_EXCEPTION("1700100103003", "查询运营菜单列表异常"),
    RBAC_RES_SAVE_EXCEPTION("1700100103004", "保存运营菜单列表异常"),
    RBAR_SET_HASHSET_FAILED("1700100103005", "查询账号列表异常"),
    /** 账号 */
    RBAC_ACCOUNT_GET_EXCEPTION("1700100103005", "查询账号列表异常"),
    RBAC_ACCOUNT_SAVE_EXCEPTION("1700100103006", "保存账号列表异常"),
    SUPER_ACCOUNT_OPT_EXCEPTION("1700100103007", "不可操作超级管理员"),
    /** 图片 */
    IMAGE_SIZE_ERROR("1700100107002", "图片大小校验失败"),
    IMAGE_FILE_ERROR("1700100107003", "图片格式校验失败"),
    UNIQUE_CONFLICT("1700100107004", "新增条目已存在"),
    ;

    public ErrorEntity entity = new ErrorEntity();

    ReturnCode(String errcode, String errmsg) {
        this.entity.errcode = errcode;
        this.entity.errmsg = errmsg;
    }

    @Override
    public String getCode() {
        return this.entity.errcode;
    }

    @Override
    public String getMsg() {
        return this.entity.errmsg;
    }

    @Override
    public String getDescription() {
        return this.getMsg();
    }

    @Override
    public String getKey() {
        return this.getCode();
    }

    class ErrorEntity {
        public String errcode;
        public String errmsg;
    }

}