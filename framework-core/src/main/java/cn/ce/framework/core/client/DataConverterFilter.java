package cn.ce.framework.core.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

public class DataConverterFilter extends IoFilterAdapter
{
  private static Logger logger = Logger.getLogger(DataConverterFilter.class);

  public void messageReceived(IoFilter.NextFilter nextFilter, IoSession session, Object message) throws Exception
  {
    logger.info("#messageReceived# message:" + message);
    ResponseMessage response = null;
    try {
      response = (ResponseMessage)GsonUtils.getJson(message.toString(), ResponseMessage.class);
    } catch (Exception e) {
      response = getErrorMsg("4", "messageReceived：Json format error.");
    }
    super.messageReceived(nextFilter, session, response);
  }

  private ResponseMessage getErrorMsg(String status, String msg) {
    logger.error("#getErrorMsg# status[" + status + "],msg[" + msg + "]");
    ResponseMessage response = new ResponseMessage(status, msg, "");
    return response;
  }

  public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    logger.error("连接出现异常。。。" + cause.getMessage());
    session.close(true);
  }
}