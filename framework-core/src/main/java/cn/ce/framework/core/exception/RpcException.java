/**        
 * 类名称：RpcException.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2018年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.exception;

/**
 * @author lenovo
 *
 */
public class RpcException extends RuntimeException {
	/** Serial version UID */
	private static final long serialVersionUID = -2641602465962449893L;

	/** 未知异常 */
	public static final int UNKNOWN_EXCEPTION = 0;

	/** 网络异常 */
	public static final int NETWORK_EXCEPTION = 1;

	/** 超时异常 */
	public static final int TIMEOUT_EXCEPTION = 2;

	/** 业务异常 */
	public static final int BIZ_EXCEPTION = 3;

	/** 拒绝服务 */
	public static final int FORBIDDEN_EXCEPTION = 4;

	/** 配置异常 */
	public static final int CONFIG_EXCEPTION = 5;

	/** 错误码 */
	private int code;

	public RpcException() {
		super();
	}

	public RpcException(String message, Throwable cause) {
		super(message, cause);
	}

	public RpcException(String message) {
		super(message);
	}

	public RpcException(Throwable cause) {
		super(cause);
	}

	public RpcException(int code) {
		super();
		this.code = code;
	}

	public RpcException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public RpcException(int code, String message) {
		super(message);
		this.code = code;
	}

	public RpcException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/**
	 * getter method
	 * 
	 * @see RpcException#code
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * setter method
	 * 
	 * @see RpcException#code
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public boolean isBiz() {
		return code == BIZ_EXCEPTION;
	}

	public boolean isForbidded() {
		return code == FORBIDDEN_EXCEPTION;
	}

	public boolean isTimeout() {
		return code == TIMEOUT_EXCEPTION;
	}

	public boolean isNetwork() {
		return code == NETWORK_EXCEPTION;
	}

	public boolean isConfig() {
		return code == CONFIG_EXCEPTION;
	}
}
