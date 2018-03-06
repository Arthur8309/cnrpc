package cn.ce.framework.core.minaserver;

import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import cn.ce.framework.core.base.BaseMapping;
import cn.ce.framework.core.tools.GsonUtils;
import cn.ce.framework.core.tools.RecErrorInfo;
import cn.ce.framework.core.tools.RequestBean;

public class MyHandler extends IoHandlerAdapter {
	private final Logger LOG = Logger.getLogger(MyHandler.class);
	
	public static ConcurrentHashMap<Long, IoSession> sessionsConcurrentHashMap = new ConcurrentHashMap<Long, IoSession>();

	@Override
	// 当接口中其他方法抛出异常未被捕获时触发此方法
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		session.close(true);
		LOG.warn("session occured exception, so close it." + cause.getMessage());
	}

	@Override
	// 当客户端发送的消息到达时:
	public void messageReceived(IoSession session, Object message) throws Exception {
		long sTime = System.currentTimeMillis();

		String msg = message.toString();
		LOG.info("request : " + msg);
		boolean result = true;
		try {
			RequestBean rb = (RequestBean) GsonUtils.getJson(msg.toString(), RequestBean.class);
			if ((rb.getMethod() != null) && (!rb.getMethod().trim().equals(""))) {
				msg = BaseMapping.getInstance().baseCall(rb.getMethod(), rb.getParams());
			} else {
				msg = RecErrorInfo.reWebReqMethodError();
			}
			rb = null;
		} catch (Exception e) {
			msg = RecErrorInfo.reWebParamBeanIntoError();
			result = false;
			LOG.error("ServerHandler.messageReceived Exception", e);
		}

		LOG.info("response : " + msg);
		session.write(msg).awaitUninterruptibly();
		session.close(false);
	}

	@Override
	// 当一个新客户端连接后触发此方法
	public void sessionCreated(IoSession session) throws Exception {
		LOG.warn("remote client [" + session.getRemoteAddress().toString() + "] connected.");
		// my
		Long time = System.currentTimeMillis();
		session.setAttribute("id", time);
		sessionsConcurrentHashMap.put(time, session);
	}

	@Override
	// 当一个客户端关闭时
	public void sessionClosed(IoSession session) throws Exception {
		LOG.warn("sessionClosed.");
		session.close(true);
		// my
		sessionsConcurrentHashMap.remove(session.getAttribute("id"));
	}

	@Override
	// 当连接空闲时触发此方法
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		LOG.warn("session idle, so disconnecting......");
		session.close(true);
		LOG.warn("disconnected.");
	}

	@Override
	// 当一个客端端连结进入时
	public void sessionOpened(IoSession session) throws Exception {
		LOG.warn("sessionOpened.");
		//
		// session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDLE);
	}
}