/**        
 * 类名称：ServerMain.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2018年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.test.server;

import cn.ce.framework.core.minaserver.Server;

/**
 * @author lenovo
 *
 */
public class ServerMain {

	public static void main(String[] args) {
		/* Server config use spring xml see applicationContext-mina。xml */
		Server server = new Server("classpath*:conf/applicationContext-mina.xml");
		server.start();
	}
}
