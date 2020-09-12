package com.ilegra.sales.utils;

import java.util.Arrays;
import java.util.List;

public class DataUtil {
	public DataUtil() {
	}

	public static List<String> splitData(String content, String delimiter) {
		return Arrays.asList(content.split(delimiter));
	}

}
