package com.ilegra.sales.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ilegra.sales.enums.DataTypeEnum;
import com.ilegra.sales.model.Client;
import com.ilegra.sales.model.Data;
import com.ilegra.sales.model.Item;
import com.ilegra.sales.model.Sale;
import com.ilegra.sales.model.Salesman;
import com.ilegra.sales.utils.ConstantUtil;
import com.ilegra.sales.utils.DataUtil;

@Component
public class FileInput {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileInput.class.getName());

	public static void process(File file) {
		LOGGER.info("Processando arquivo de vendas: " + file);

		List<Data> dataList = new ArrayList<>();

		if (isValidFile(file)) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line = br.readLine();

				while (line != null) {
					List<String> fields = DataUtil.splitData(line, ConstantUtil.FIELD_DELIMITER);
					addData(fields, dataList);
					line = br.readLine();
				}
				FileOut.process(file, dataList);
				br.close();
				file.delete();

			} catch (Exception e) {
				LOGGER.error("Erro ao processar arquivo: " + e.getMessage());
			}

		} else {
			LOGGER.error("Arquivo inválido: " + file.getName());
		}
		
		LOGGER.info("Arquivo de vendas processado com sucesso: " + file);
	}

	public static void addData(List<String> fields, List<Data> dataList) {
		String type = fields.get(0).toString();
		Data data = null;

		if (type.equals(DataTypeEnum.SALESMAN.getCode())) {
			data = new Salesman(fields.get(1), fields.get(2), new BigDecimal(fields.get(3)));
			dataList.add(data);
			return;
		}

		if (type.equals(DataTypeEnum.CLIENT.getCode())) {
			data = new Client(fields.get(1), fields.get(2), fields.get(3));
			dataList.add(data);
			return;
		}

		if (type.equals(DataTypeEnum.SALE.getCode())) {
			data = new Sale(Long.parseLong(fields.get(1)), getItems(fields.get(2)), fields.get(3));
			dataList.add(data);
			return;
		}
	}

	public static List<Item> getItems(String field) {
		List<Item> items = new ArrayList<>();

		List<String> saleItems = DataUtil.splitData(field.replace("[", "").replace("]", ""),
				ConstantUtil.SALE_ITEMS_DELIMITER);
		for (String saleItem : saleItems) {
			List<String> fieldsItem = DataUtil.splitData(saleItem, ConstantUtil.FIELD_ITEM_DELIMITER);
			Item item = new Item(Long.parseLong(fieldsItem.get(0)), Integer.parseInt(fieldsItem.get(1)),
					new BigDecimal(fieldsItem.get(2)));
			items.add(item);
		}

		return items;
	}

	public static Boolean isValidFile(File file) {
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		return file.exists() && file.isFile() && file.length() > BigDecimal.ZERO.longValue()
				&& mimeType.equals(ConstantUtil.MIMETYPE);
	}
}
