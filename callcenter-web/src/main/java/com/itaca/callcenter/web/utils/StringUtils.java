package com.itaca.callcenter.web.utils;

import java.util.Map;
import java.util.Map.Entry;

public final class StringUtils {
	private StringUtils() {

	}

	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}

	public static void capitalizeStringValues(Map<String, Object> map) {
		for (Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof String) {
				entry.setValue(((String) value).toUpperCase());
			}
		}
	}

}
