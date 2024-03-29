package com.algz.platform.utility;

import java.io.Serializable;

/**
 * @Description: 统一返回实体
 * @author algz
 *
 * @param <T>
 */
public class JsonResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

//	result.getStatusMsg()
//	{
//        status: 'ok',
//        type,
//        currentAuthority: 'admin',
//      }
	private String status;
	
	private String currentAuthority;
	
	private String type="account";
	
	private Boolean success;
    private Integer statusCode;
    private String statusMsg;
    
    private T data;

    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
        this.status=success?"ok":"error";
        this.statusCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
		this.statusMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }

    public JsonResult(boolean success,String msg) {
        this.success = success;
        this.status=success?"ok":"error";
        this.statusCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
		this.statusMsg = msg;
    }
    
    public JsonResult(boolean success, ResultCode resultEnum) {
        this.success = success;
        this.status=success?"ok":"error";
        this.statusCode = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.statusMsg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        
     }

    public JsonResult(boolean success, T data) {
        this.success = success;
        this.status=success?"ok":"error";
        this.statusCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.statusMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.data = data;
    }

    public JsonResult(boolean success, ResultCode resultEnum, T data) {
        this.success = success;
        this.status=success?"ok":"error";
        this.statusCode = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.statusMsg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        this.data = data;
    }

    public Boolean getSuccess() {
        return true;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

 

    public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    
    
    public String getCurrentAuthority() {
		return currentAuthority;
	}

	public void setCurrentAuthority(String currentAuthority) {
		this.currentAuthority = currentAuthority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	/**
     * @Author: Hutengfei
     * @Description: 返回码定义
     * 规定:
     * #1表示成功
     * #1001～1999 区间表示参数错误
     * #2001～2999 区间表示用户错误
     * #3001～3999 区间表示接口异常
     * @Date Create in 2019/7/22 19:28
     */
    public enum ResultCode {
        /* 成功 */
        SUCCESS(200, "成功"),

        /* 默认失败 */
        COMMON_FAIL(999, "失败"),

        /* 权限错误 */
        NO_PERMISSION(403, "没有权限"),
        
        USER_ACCOUNT_EXIT(2010, "账号退出"),
        
        /* 参数错误：1000～1999 */
        PARAM_NOT_VALID(1001, "参数无效"),
        PARAM_IS_BLANK(1002, "参数为空"),
        PARAM_TYPE_ERROR(1003, "参数类型错误"),
        PARAM_NOT_COMPLETE(1004, "参数缺失"),

        /* 用户错误 */
        USER_NOT_LOGIN(2001, "用户未登录"),
        USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
        USER_CREDENTIALS_ERROR(2003, "密码错误"),
        USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
        USER_ACCOUNT_DISABLE(2005, "账号不可用"),
        USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
        USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
        USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
        USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线");
    	
    	
    	
        private Integer code;
        private String message;

        ResultCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * 根据code获取message
         *
         * @param code
         * @return
         */
        public static String getMessageByCode(Integer code) {
            for (ResultCode ele : values()) {
                if (ele.getCode().equals(code)) {
                    return ele.getMessage();
                }
            }
            return null;
        }
    }
}


