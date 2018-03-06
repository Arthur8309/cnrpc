package cn.ce.framework.core.minaserver;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ce.framework.core.base.BaseMapping;
import cn.ce.framework.core.tools.ArrayClasses;
import cn.ce.framework.core.tools.BeanContext;
import cn.ce.framework.core.tools.Utils;

/**
 * Hello world!
 *
 */
public class Server {

	private final Logger LOG = Logger.getLogger(Server.class);
	private static ClassPathXmlApplicationContext context;

	public Server(String configLocation) {
		this.configLocation = configLocation;
	}

	private String configLocation;

	public void start() {
		try {
			if (Utils.isNotEmpty(this.configLocation)) {
				Server.context = new ClassPathXmlApplicationContext(this.configLocation);
				Server.context.refresh();
			}
			ArrayClasses ac = (ArrayClasses) Server.context.getBean("arrayClasses");

			BeanContext.init(Server.context);
			LOG.info("server start sucess!");
			if (!BaseMapping.Init(ac)) {
				stop();
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			if (this.context != null) {
				this.context.close();
				this.context.destroy();
			}
		} catch (Exception e) {
			System.out.println("[应用服务器]停止失败，请查看异常日志" + e);
		}
	}
	//
	// public static void main(String[] args) {
	// App app = new App("classpath*:conf/applicationContext-mina.xml");
	// app.start();
	// }
}
