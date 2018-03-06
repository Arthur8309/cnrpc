package cn.ce.framework.core.tools;


public class RequestBean
{
  private String method;
  private String params;

  public String getMethod()
  {
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
}