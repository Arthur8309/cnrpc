package cn.ce.framework.core.client;

import java.io.Serializable;

public class RequestMessage
  implements Serializable
{
  private static final long serialVersionUID = -6111220358041616436L;
  private String method;
  private String params;
  private String server;

  public RequestMessage()
  {
  }

  public RequestMessage(String method, String params)
  {
    this.method = method;
    this.params = params;
  }

  public RequestMessage(String method, String params, String server)
  {
    this.method = method;
    this.params = params;
    this.server = server;
  }

  public String getMethod() {
    return this.method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getParams() {
    return this.params;
  }

  public void setParams(String params) {
    this.params = params;
  }

  public String getServer() {
    return this.server;
  }

  public void setServer(String server) {
    this.server = server;
  }
}