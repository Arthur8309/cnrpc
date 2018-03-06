package cn.ce.framework.core.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.ce.framework.core.base.BaseService;
import cn.ce.framework.core.tools.ArrayClasses;
import cn.ce.framework.core.tools.BeanContext;
import cn.ce.framework.core.tools.GsonUtils;
import cn.ce.framework.core.tools.IResponseData;
import cn.ce.framework.core.tools.JsonMapping;
import cn.ce.framework.core.tools.RecErrorInfo;

public class Mapping {

	private static final Logger log = Logger.getLogger("IECCORE");
	private static Mapping INSTANCE = null;
	private static Map<String, Method> jsonServiceMap = new HashMap();
	private static BaseService baseService = BaseService.getInstance();

	public static final boolean Init(ArrayClasses ac) {
		if (INSTANCE == null) {
			INSTANCE = new Mapping();
		}

		boolean rt = true;
		try {
			int len = ac.getClassNames().length;
			Class[] clazzArray = new Class[len];
			int i = 0;
			for (i = 0; i < len; i++) {
				clazzArray[i] = Class.forName(ac.getClassNames()[i]);
			}

			for (i = 0; i < len; i++)
				if (!initJsonServiceMap(clazzArray[i])) {
					rt = false;
					break;
				}
		} catch (Exception e) {
			log.error("[BaseMapping error]: class init failed,", e);
			rt = false;
		}
		return rt;
	}

	public static final Mapping getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Mapping();
		}
		return INSTANCE;
	}

	private static boolean initJsonServiceMap(Class<?> clazz) {
		Method[] method = clazz.getDeclaredMethods();
		for (int i = 0; i < method.length; i++) {
			JsonMapping service = (JsonMapping) method[i].getAnnotation(JsonMapping.class);

			if (service != null) {
				String serviceName = service.mappingName();

				if (jsonServiceMap.containsKey(serviceName)) {
					log.error("[BaseMapping error]: serviceName conflict," + serviceName);
					return false;
				}

				log.info(serviceName + ">>>>>" + clazz.getName());

				jsonServiceMap.put(serviceName, method[i]);
			}
		}
		return true;
	}

	public String baseCall(String methodName, String json) {
		Method method = (Method) jsonServiceMap.get(methodName);
		if (method != null) {
			try {
				return serviceExecutor(method, json);
			} catch (Exception e) {
				log.error("[BaseService error]:", e);
			}
		}
		return RecErrorInfo.reWebReqMethodError();
	}

	private String serviceExecutor(Method method, String json)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		JsonMapping service = (JsonMapping) method.getAnnotation(JsonMapping.class);
		String className = method.getDeclaringClass().getSimpleName();
		Object obj = BeanContext.getBean(toLowerCaseFirstOne(className));
		Class[] parameterTypes = method.getParameterTypes();

		Object[] params = new Object[parameterTypes.length];
		Object result = null;

		int i = 0;
		for (Class parameterType : parameterTypes) {
			params[(i++)] = GsonUtils.getJson(json, parameterType);
		}

		result = method.invoke(obj, params);

		Class returnType = method.getReturnType();

		if (Void.class.isAssignableFrom(returnType)) {
			return baseService.retJson();
		}

		if (service.autoJson()) {
			if (IResponseData.class.isAssignableFrom(returnType)) {
				IResponseData data = (IResponseData) result;
				return GsonUtils.retJson(data.getCode(), data.getData(), data.getDesc());
			}

			return GsonUtils.retJson(result);
		}

		return (String) result;
	}

	public static String toLowerCaseFirstOne(String s) {
		if ((s == null) || (s.isEmpty()))
			return s;

		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		}
		return Character.toLowerCase(s.charAt(0)) + s.substring(1);
	}
}
