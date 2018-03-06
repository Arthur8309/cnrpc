package cn.ce.framework.core.client;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class MinaLoadPrpo
{
  private static final Logger logger = Logger.getLogger(MinaLoadPrpo.class);

  private static final MinaLoadPrpo INSTANCE = new MinaLoadPrpo();

  private String configPath = "conf/minaConfig.properties";

  private Properties configuration = null;

  public static final MinaLoadPrpo getInstance()
  {
    return INSTANCE;
  }

  private MinaLoadPrpo()
  {
    load();
  }

  private void load()
  {
    if (this.configuration == null)
    {
      try {
        Properties prop = System.getProperties();
        Object fileName = prop.get("sys.config.name");
        if ((fileName != null) && (!"".equals((String)fileName))) {
          logger.info("通过环境变量获取配置信息>>>>" + (String)fileName);
          this.configPath = ((String)fileName);
        }
      } catch (Exception e) {
        logger.error("配置文件不存在，请检查系统参数[sys.config.name]的配置。");
      }

      InputStream is = MinaLoadPrpo.class.getClassLoader().getResourceAsStream(this.configPath);
      try {
        this.configuration = new Properties();
        this.configuration.load(is);
        logger.info("Loading " + this.configPath + " success");
      } catch (Exception e) {
        logger.error("Loading " + this.configPath + " error " + e);
        System.exit(-1);
      }
    }
  }

  public String getValue(String key)
  {
    return this.configuration.getProperty(key);
  }

  public int getIntValue(String key)
  {
    String valueStr = this.configuration.getProperty(key);
    try {
      return Integer.parseInt(valueStr);
    } catch (Exception e) {
      logger.error(e);
    }
    return -1;
  }

  public long getLongValue(String key)
  {
    String valueStr = this.configuration.getProperty(key);
    try {
      return Long.parseLong(valueStr);
    } catch (Exception e) {
      logger.error(e);
    }
    return -1L;
  }
}