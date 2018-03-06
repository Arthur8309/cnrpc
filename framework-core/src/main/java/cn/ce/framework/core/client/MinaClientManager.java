package cn.ce.framework.core.client;

import com.google.gson.JsonSyntaxException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClientManager
{
  private static Logger logger = Logger.getLogger(MinaClientManager.class);

  private static final MinaClientManager INSTANCE = new MinaClientManager();

  IoConnector connector = null;

  private MinaClientManager() {
    connInit();
  }

  private void connInit()
  {
    this.connector = new NioSocketConnector();

    this.connector.setConnectTimeoutMillis(MinaConfig.CONNECT_TIMEOUT);

    this.connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "GBK" )))); //设置编码过滤器

    this.connector.getFilterChain().addLast("sigleThread", 
      new ExecutorFilter(Executors.newSingleThreadExecutor()));

    this.connector.getFilterChain().addLast("dataConverter", 
      new DataConverterFilter());

    IoSessionConfig cfg = this.connector.getSessionConfig();

    cfg.setUseReadOperation(true);

    cfg.setMaxReadBufferSize(MinaConfig.READBUFFER_MAX_SIZE);
  }

  public static final MinaClientManager getInstance()
  {
    return INSTANCE;
  }

  public ResponseMessage getMessage(RequestMessage request)
  {
    long sTime = System.currentTimeMillis();

    if ((this.connector == null) || (this.connector.isDisposed())) {
      connInit();
    }

    ResponseMessage response = new ResponseMessage();

    if ((this.connector != null) && (this.connector.isDisposed())) {
      response.setDesc("client connect error");
      response.setCode("3");
      return response;
    }

    String serverIp = MinaConfig.SERVER_IP;
    Integer serverPort = Integer.valueOf(MinaConfig.SERVER_PORT);
    if (request.getServer() != null) {
      serverIp = MinaConfig.getServerIP(request.getServer());
      serverPort = MinaConfig.getServerPort(request.getServer());

      if (serverPort.intValue() == -1) {
        serverIp = MinaConfig.SERVER_IP;
        serverPort = Integer.valueOf(MinaConfig.SERVER_PORT);
      }
    }

    logger.info("connect to " + serverIp + "\t" + serverPort);
    ConnectFuture future = this.connector.connect(new InetSocketAddress(serverIp, serverPort.intValue()));
    ReadFuture readFuture = null;
    IoSession session = null;
    try
    {
      future.awaitUninterruptibly();

      if ((future.isDone()) && 
        (!future.isConnected())) {
        logger.info("==========close conn by future disconnect");
        throw new Exception();
      }

      session = future.getSession();
      String sendMsg = GsonUtils.toJson(request);
      logger.info("client send Json : " + sendMsg);

      session.write(sendMsg);

      readFuture = session.read();

      if (readFuture.awaitUninterruptibly(MinaConfig.WAIT_OUT_TIME, TimeUnit.SECONDS)) {
        response = (ResponseMessage)readFuture.getMessage();
        logger.info("client read data：" + response.getData());
      }
      else {
        response.setDesc("wait server out time");
        response.setCode("2");
        logger.info("wait server out time");
      }
    }
    catch (JsonSyntaxException e) {
      logger.error("json format error, " + e);
      response.setDesc("json format error");
      response.setCode("4");
    } catch (Exception e) {
      logger.error("client connect error,sess=" + session + ",res=" + response + "," + e);
      e.printStackTrace();
      response.setDesc("client connect error");
      response.setCode("3");
    } finally {
      if ((session != null) && (!session.isClosing())) {
        session.getCloseFuture().awaitUninterruptibly();
        session.close(false);
      }
      logger.info("==========close conn");
    }
    return response;
  }
}