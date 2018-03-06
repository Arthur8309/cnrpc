package cn.ce.framework.core.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.ce.framework.core.tools.GsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService {
	private static BaseService BaseService = new BaseService();

	public static BaseService getInstance() {
		return BaseService;
	}

	public String retJson() {
		return retJson("0", "", "success");
	}

	public String retJson(Object data) {
		return retJson("0", data, "success");
	}

	public String retJson(String errcode, String errdesc) {
		return retJson(errcode, "", errdesc);
	}

	public String retJson(List<?> list, Object page) {
		Map map = new HashMap();
		map.put("list", list);
		map.put("page", page);
		return retJson(map);
	}

	public String retPageJson(Object data) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().enableComplexMapKeySerialization()
				.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS").setPrettyPrinting().create();

		return retJson("0", gson.toJson(data), "success");
	}

	public String retJson(String code, Object data, String desc) {
		Map map = new HashMap();

		String retJson = null;

		map.put("code", code);

		if ((data != null) && (!"".equals(data.toString().trim()))) {
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()
					.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS").create();
			map.put("data", gson.toJson(data));
		} else {
			map.put("data", "");
		}
		if ((desc != null) && (!"".equals(desc)))
			map.put("desc", desc);
		else {
			map.put("desc", "");
		}

		retJson = GsonUtils.toJson(map);
		map.clear();
		map = null;
		return retJson;
	}
}