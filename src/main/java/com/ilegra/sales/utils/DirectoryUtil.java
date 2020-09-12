package com.ilegra.sales.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DirectoryUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryUtil.class.getName());

	public static final void checkDirectories() {
		boolean success = false;
		try {
			if (!new File(getPathData()).exists()) {
				success = new File(getPathData()).mkdir();
			}

			if (!new File(getPathIn()).exists()) {
				success = new File(getPathIn()).mkdir();
			}

			if (!new File(getPathOut()).exists()) {
				success = new File(getPathOut()).mkdir();
			}

		} catch (Exception e) {
			LOGGER.error("Criação de diretórios com sucesso: " + success + " Erro: " + e.getMessage());
		}
	}

	public static final String getPathData() {
		return getHomePath().concat(getDelimiter()).concat("data");
	}

	public static final String getPathIn() {
		return getPathData().concat(getDelimiter()).concat("in");
	}

	public static final String getPathOut() {
		return getPathData().concat(getDelimiter()).concat("out");
	}

	public static final String getHomePath() {
		return System.getProperty("user.home");
	}

	public static final String getDelimiter() {
		return System.getProperty("os.name").contains("Windows") ? "\\" : "/";
	}
}
