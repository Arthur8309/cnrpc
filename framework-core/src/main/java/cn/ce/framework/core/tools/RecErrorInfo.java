package cn.ce.framework.core.tools;

public class RecErrorInfo
{
  public static String reWebReqModuleError()
  {
    return reError("web request module not exists", "11");
  }

  public static String reWebReqMethodError()
  {
    return reError("web request method not exists", "12");
  }

  public static String reWebParamBeanIntoError()
  {
    return reError("web pass parameters error to java bean into error", "13");
  }

  public static String reWebParamError()
  {
    return reError("web pass parameters error", "14");
  }

  public static String reAppSysError()
  {
    return reError("web pass parameters error", "15");
  }

  public static String reAppSysError(String errCode, String errMsg) {
    return reError(errMsg, errCode);
  }

  public static String reAppDBError()
  {
    return reError("web pass parameters error", "16");
  }

  public static String reAppFieldsDefineError()
  {
    return reError("web pass parameters error", "16");
  }

  public static String reAppDBError(String errCode, String errMsg)
  {
    return reError(errMsg, errCode);
  }

  private static String reError(String retMsg, String errcode)
  {
    StringBuffer reJson = new StringBuffer("");

    reJson.append("{\"code\":\"" + errcode + "\",\"data\":\"\",\"desc\":\"" + retMsg + "\"}");

    return reJson.toString();
  }
}