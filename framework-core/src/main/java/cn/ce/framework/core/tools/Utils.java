package cn.ce.framework.core.tools;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Utils {

	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		}
		if (pObj == "") {
			return true;
		}
		if ((pObj instanceof String)) {
			if (((String) pObj).length() == 0)
				return true;
		} else if ((pObj instanceof Collection)) {
			if (((Collection) pObj).size() == 0)
				return true;
		} else if (((pObj instanceof Map)) && (((Map) pObj).size() == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null) {
			return false;
		}
		if (pObj == "") {
			return false;
		}
		if ((pObj instanceof String)) {
			if (((String) pObj).length() == 0)
				return false;
		} else if ((pObj instanceof Collection)) {
			if (((Collection) pObj).size() == 0)
				return false;
		} else if (((pObj instanceof Map)) && (((Map) pObj).size() == 0)) {
			return false;
		}

		return true;
	}

	public static boolean isNotEmpty(List t) {
		if ((t != null) && (t.size() > 0)) {
			return true;
		}
		return false;
	}
}
