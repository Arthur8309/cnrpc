/**        
 * 类名称：ServerNode.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2018年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.shaded.com.google.common.base.Objects;

/**
 * @author lenovo
 *
 */
public class ServerNode {

	/** 服务ip地址 */
	private String ip;

	/** 服务端口 */
	private int port;

	/** 扩展标识（用于client端） */
	private String ext;

	/**
	 * @param ip
	 * @param port
	 */
	public ServerNode(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	/**
	 * @param ip
	 * @param port
	 * @param ext
	 */
	public ServerNode(String ip, int port, String ext) {
		super();
		this.ip = ip;
		this.port = port;
		this.ext = ext;
	}

	/**
	 * 获取ServerNode
	 * <p>
	 * 
	 * @param host
	 * @param port
	 * @return {@link ServerNode}
	 */
	public static ServerNode fromParts(String host, int port) {
		return new ServerNode(host, port);
	}

	/**
	 * 生成服务地址<br>
	 * Server端：ip:port <br>
	 * Client端：ip:port:i_节点序列号 <br>
	 * <p>
	 * 
	 * @return ip:port
	 */
	public String genAddress() {
		return ip + ":" + port + (StringUtils.isEmpty(ext) ? "" : (":" + ext));
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof ServerNode) {
			ServerNode that = (ServerNode) other;
			return Objects.equal(this.ip, that.ip) && this.port == that.port && Objects.equal(this.ext, that.ext);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(ip, port, ext);
	}

	@Override
	public String toString() {
		return "ServerNode [ip=" + ip + ", port=" + port + "]";
	}

	/**
	 * getter method
	 * 
	 * @see ServerNode#ip
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * setter method
	 * 
	 * @see ServerNode#ip
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * getter method
	 * 
	 * @see ServerNode#port
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * setter method
	 * 
	 * @see ServerNode#port
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * getter method
	 * 
	 * @see ServerNode#ext
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * setter method
	 * 
	 * @see ServerNode#ext
	 * @param ext
	 *            the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
}
