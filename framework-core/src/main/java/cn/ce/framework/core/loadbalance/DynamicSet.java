/**        
 * 类名称：DynamicSet.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2018年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.loadbalance;

/**
 * @author lenovo
 *
 */
public interface DynamicSet<K> {
	void monitor(HostChangeMonitor<K> monitor);
}
