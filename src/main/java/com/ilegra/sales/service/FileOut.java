package com.ilegra.sales.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ilegra.sales.model.Data;
import com.ilegra.sales.utils.DirectoryUtil;

@Component
public class FileOut {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileOut.class.getName());

	public static void process(File fileIn, List<Data> data) {

		try (BufferedWriter bf = new BufferedWriter(new FileWriter(getPathOut(fileIn.getName())))) {
			bf.write(new SalesSummaryImpl(data).toString());
			bf.newLine();
		} catch (Exception e) {
			LOGGER.error("Erro ao salvar arquivo:" + e.getMessage());
		} finally {
		}
	}

	public static String getPathOut(String fileName) {
		return DirectoryUtil.getPathOut().concat(DirectoryUtil.getDelimiter()) + Instant.now().toEpochMilli() + "-summary-" + fileName;
	}
}
