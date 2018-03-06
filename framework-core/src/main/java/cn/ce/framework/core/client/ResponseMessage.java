package cn.ce.framework.core.client;

import java.io.Serializable;

public class ResponseMessage
  implements Serializable
{
  private static final long serialVersionUID = -4692119596934798174L;
  private String code;
  private String data;
  private String desc;

  public ResponseMessage()
  {
  }

  public ResponseMessage(String code, String data, String desc)
  {
    this.code = code;
    this.data = data;
    this.desc = desc;
  }

  public String getCode() {
    return this.code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getData() {
    return this.data;
  }
  public void setData(String data) {
    this.data = data;
  }
  public String getDesc() {
    return this.desc;
  }
  public void setDesc(String desc) {
    this.desc = desc;
  }
}