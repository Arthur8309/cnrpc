package cn.ce.framework.core.tools;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseData implements Serializable, IResponseData {

	private String code;
	private String desc;
	private Object data;

	public enum STATUS {
		SUCCESS("0", "成功"), ERROR("001", "错误"), ARGU("002", "非法参数"), DB("003", "数据库异常"), REQ("005", "非法请求");

		private String num;
		private String msg;

		private STATUS(String num, String msg) {
			this.num = num;
			this.msg = msg;
		}
	}

	public ResponseData(String code, String desc, Object data) {
		super();
		this.code = code;
		this.desc = desc;
		this.data = data;
	}

	public static ResponseData newResponse(String code, String desc, Object data) {
		return new ResponseData(code, desc, data);
	}

	/**
	 * 请求成功
	 * 
	 * @param @return
	 * @return ResponseData 相应对象
	 * @throws @since
	 */
	public static ResponseData newOk() {
		return newResponse(STATUS.SUCCESS.num, STATUS.SUCCESS.msg, null);
	}

	public static ResponseData newOk(String msg) {
		return new ResponseData(STATUS.SUCCESS.num, msg, null);
	}

	public static ResponseData newOk(Object data) {
		return newResponse(STATUS.SUCCESS.num, STATUS.SUCCESS.msg, data);
	}

	/**
	 * 请求成功
	 * 
	 * @param @param
	 *            msg 成功消息
	 * @param @param
	 *            data 相应数据
	 * @param @return
	 * @return ResponseData 相应对象
	 * @throws @since
	 */
	public static ResponseData newOk(String msg, Object data) {
		return newResponse(STATUS.SUCCESS.num, msg, data);
	}

	public static ResponseData newError() {
		return newResponse(STATUS.ERROR.num, STATUS.ERROR.msg, null);
	}

	public static ResponseData newError(String msg) {
		return newResponse(STATUS.ERROR.num, msg, null);
	}

	public static ResponseData newError(Object data) {
		return newResponse(STATUS.ERROR.num, STATUS.ERROR.msg, data);
	}

	/**
	 * 请求失败
	 * 
	 * @param @param
	 *            msg 失败消息
	 * @param @param
	 *            data 相应数据
	 * @param @return
	 * @return ResponseData 相应对象
	 * @throws @since
	 */
	public static ResponseData newError(String msg, Object data) {
		return newResponse(STATUS.ERROR.num, msg, data);
	}

	public static ResponseData newArgu() {
		return newResponse(STATUS.ARGU.num, STATUS.ARGU.msg, null);
	}

	public static ResponseData newArgu(String msg) {
		return newResponse(STATUS.ARGU.num, msg, null);
	}

	public static ResponseData newArgu(Object data) {
		return newResponse(STATUS.ARGU.num, STATUS.ARGU.msg, data);
	}

	/**
	 * 非法参数
	 * 
	 * @param @param
	 *            msg 失败消息
	 * @param @param
	 *            data 相应数据
	 * @param @return
	 * @return ResponseData 相应对象
	 * @throws @since
	 */
	public static ResponseData newArgu(String msg, Object data) {
		return newResponse(STATUS.ARGU.num, msg, data);
	}

	public static ResponseData newDb() {
		return newResponse(STATUS.DB.num, STATUS.DB.msg, null);
	}

	public static ResponseData newDb(String msg) {
		return newResponse(STATUS.DB.num, msg, null);
	}

	public static ResponseData newDb(Object data) {
		return newResponse(STATUS.DB.num, STATUS.DB.msg, data);
	}

	/**
	 * 数据库异常
	 * 
	 * @param @param
	 *            msg 消息
	 * @param @param
	 *            data 相应数据
	 * @param @return
	 * @return ResponseData 相应对象
	 * @throws @since
	 */
	public static ResponseData newDb(String msg, Object data) {
		return newResponse(STATUS.ARGU.num, msg, data);
	}

	public static ResponseData newReq() {
		return newResponse(STATUS.REQ.num, STATUS.REQ.msg, null);
	}

	public static ResponseData newReq(String msg) {
		return newResponse(STATUS.REQ.num, msg, null);
	}

	public static ResponseData newReq(Object data) {
		return newResponse(STATUS.REQ.num, STATUS.REQ.msg, data);
	}

	/**
	 * 非法请求
	 * 
	 * @param @param
	 *            msg 失败消息
	 * @param @param
	 *            data 相应数据
	 * @param @return
	 * @return ResponseData 相应对象
	 * @throws @since
	 */
	public static ResponseData newReq(String msg, Object data) {
		return newResponse(STATUS.REQ.num, msg, data);
	}

	/**
	 * 
	 * @return code
	 * @since
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 * @since
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return data
	 * @since
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 * @since
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 
	 * @return desc
	 * @since
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 
	 * @param desc
	 * @since
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
