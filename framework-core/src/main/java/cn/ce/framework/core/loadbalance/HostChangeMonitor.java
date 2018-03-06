/**        
 * 类名称：HostChangeMonitor.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2018年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.loadbalance;

import com.google.common.collect.ImmutableSet;

/**
 * @author lenovo
 *
 */
public interface HostChangeMonitor<T> {
	void onChange(ImmutableSet<T> hostAndPorts);
}
