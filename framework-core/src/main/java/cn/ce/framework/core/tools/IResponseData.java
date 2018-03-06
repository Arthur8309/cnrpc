package cn.ce.framework.core.tools;

public abstract interface IResponseData {
	public abstract String getCode();

	public abstract Object getData();

	public abstract String getDesc();
}