package cn.ce.framework.core.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class GsonUtils {
	private static Gson gson = new Gson();

	public static <T> T getJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static String retJson() {
		return retJson("0", "", "success");
	}

	public static String retJson(Object data) {
		return retJson("0", data, "success");
	}

	public static String retJson(String errcode, String errdesc) {
		return retJson(errcode, "", errdesc);
	}

	public static String retJson(String code, Object data, String desc) {
		Map map = new HashMap();

		String retJson = null;

		map.put("code", code);

		if ((data != null) && (!"".equals(data.toString().trim()))) {
			map.put("data", gson.toJson(data));
		} else {
			map.put("data", "");
		}
		if ((desc != null) && (!"".equals(desc)))
			map.put("desc", desc);
		else {
			map.put("desc", "");
		}

		retJson = toJson(map);
		map.clear();
		map = null;
		return retJson;
	}
}