package cn.ce.framework.core.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class BeanContext
{
  private static ApplicationContext[] ApplicationContexts;
  private static String[] ConfigLocations = { "classpath*:conf/applicationContext-services.xml" };
  private static volatile boolean isInited = false;

  public static void setConfigLocations(String[] configLocations) {
    ConfigLocations = configLocations;
  }

  public static void init(ApplicationContext parent) {
    if (!isInited)
    {
      if (ConfigLocations.length > 0) {
        ApplicationContexts = new ApplicationContext[ConfigLocations.length];
        int i = 0;
        for (String configLocation : ConfigLocations) {
          ApplicationContexts[(i++)] = new ClassPathXmlApplicationContext(new String[] { configLocation }, parent);
        }
      }
      else
      {
        throw new RuntimeException("没有配置暴露服务的spring配置文件，请启动ApplicationServer时调用BeanContext.init(String[] configLocations)初始化暴露的服务！");
      }
    }
  }

  public static ApplicationContext[] getApplicationContext()
  {
    return ApplicationContexts;
  }

  public static Object getBean(String beanId)
  {
    Object bean = null;
    for (ApplicationContext applicationContext : ApplicationContexts) {
      bean = applicationContext.getBean(beanId);
      if (bean != null) {
        return bean;
      }
    }

    return bean;
  }

  public static <T> T getBean(String beanId, Class<T> clazz)
  {
    Object bean = null;
    for (ApplicationContext applicationContext : ApplicationContexts) {
      bean = applicationContext.getBean(beanId, clazz);
      if (bean != null) {
        return (T) bean;
      }
    }

    return (T) bean;
  }

  public static Resource getResource(String name) {
    Resource resource = null;
    for (ApplicationContext applicationContext : ApplicationContexts) {
      resource = applicationContext.getResource(name);
      if (resource != null) {
        return resource;
      }
    }

    return resource;
  }
}