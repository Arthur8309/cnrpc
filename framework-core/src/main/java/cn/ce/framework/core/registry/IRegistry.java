/**        
 * 类名称：IRegistry.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2018年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.registry;

import cn.ce.framework.core.exception.RpcException;
import cn.ce.framework.core.loadbalance.DynamicHostSet;

/**
 * @author lenovo
 *
 */
public interface IRegistry {
	/**
	 * 注册<br>
	 * 包括：provider和client
	 * <p>
	 * 
	 * @param config
	 *            配置信息
	 * @throws RpcException
	 */
	void register(String config) throws RpcException;

	/**
	 * 获取所以服务
	 * <p>
	 * 
	 * @return
	 */
	DynamicHostSet findAllService();

	/**
	 * 服务注销
	 * <p>
	 */
	void unregister();
}
