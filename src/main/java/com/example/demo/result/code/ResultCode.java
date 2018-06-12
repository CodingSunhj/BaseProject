//package com.example.demo.errorcode;
//
//public class ResultCode {
//   public static final ResultCode SUCCESS;
//   /* 参数错误：10001-19999 */
////   public static final ErrorCode PARAM_IS_INVALID;
////   public static final ErrorCode PARAM_IS_BLANK;
////   public static final ErrorCode PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
////   public static final ErrorCode PARAM_NOT_COMPLETE(10004, "参数缺失"),
//
//   /* 用户错误：20001-29999*/
////   public static final ErrorCode USER_NOT_LOGGED_IN(20001, "用户未登录"),
////   public static final ErrorCode USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
////   public static final ErrorCode USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
////   public static final ErrorCode USER_NOT_EXIST(20004, "用户不存在"),
////   public static final ErrorCode USER_HAS_EXISTED(20005, "用户已存在"),
////   public static final ErrorCode LOGIN_CREDENTIAL_EXISTED(20006, "凭证已存在"),
//
//   /* 业务错误：30001-39999 */
////   public static final ErrorCode SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "业务错误"),
//
//   /* 系统错误：40001-49999 */
////   public static final ErrorCode SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
//
//   /* 数据错误：50001-599999 */
////   public static final ErrorCode RESULE_DATA_NONE(50001, "数据未找到"),
////   public static final ErrorCode DATA_IS_WRONG(50002, "数据有误"),
////   public static final ErrorCode DATA_ALREADY_EXISTED(50003, "数据已存在"),
//
//   /* 接口错误：60001-69999 */
////   public static final ErrorCode INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
////   public static final ErrorCode INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
////   public static final ErrorCode INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
////   public static final ErrorCode INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
////   public static final ErrorCode INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
////   public static final ErrorCode INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),
//
//   /* 权限错误：70001-79999 */
////   public static final  ErrorCode PERMISSION_NO_ACCESS(70001, "无访问权限"),
////   public static final ErrorCode RESOURCE_EXISTED(70002, "资源已存在"),
////   public static final ErrorCode RESOURCE_NOT_EXISTED(70003, "资源不存在");
//
//
//   static{
//      SUCCESS = new ResultCode(1,"SUCCESS");
////      PARAM_IS_INVALID = new ErrorCode(10001, "参数无效");
////      PARAM_IS_BLANK = new ErrorCode(10002, "参数为空");
//   }
//
//   private Integer code;
//   private String msg;
//
//   public ResultCode(Integer code, String msg) {
//      this.code = code;
//      this.msg = msg;
//   }
//
//   public Integer getCode() {
//      return code;
//   }
//
//   public void setCode(Integer code) {
//      this.code = code;
//   }
//
//   public String getMsg() {
//      return msg;
//   }
//
//   public void setMsg(String msg) {
//      this.msg = msg;
//   }
//}
