package com.ilegra.sales.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ilegra.sales.model.Client;
import com.ilegra.sales.model.Data;
import com.ilegra.sales.model.Sale;
import com.ilegra.sales.model.Salesman;

public class SalesSummaryImpl implements SalesSummary {

	List<Data> data = new ArrayList<Data>();

	public SalesSummaryImpl(List<Data> data) {
		this.data = data;
	}

	@Override
	public Long countClient() {
		return data
				.stream()
				.filter(Client.class::isInstance)
				.distinct()
				.count();
	}

	@Override
	public Long countSalesman() {
		return data
				.stream()
				.filter(Salesman.class::isInstance)
				.distinct()
				.count();
	}

	@Override
	public Sale getBiggestSale() {
		return data
				.stream()
				.filter(Sale.class::isInstance)
				.map(Sale.class::cast)
				.max(Comparator.comparing(Sale::getTotal))
				.orElse(null);
	}

	@Override
	public Sale getLowestSale() {
		return data
				.stream()
				.filter(Sale.class::isInstance)
				.map(Sale.class::cast)
				.min(Comparator.comparing(Sale::getTotal))
				.orElse(null);
	}
	
	@Override
	public String toString() {
		return Arrays.asList(
				"Quantidade de clientes no arquivo de entrada: " + countClient(),
				"Quantidade de vendedores no arquivo de entrada: " + countSalesman(),
				"ID da venda mais cara: " + getBiggestSale().getId(),
				"Pior vendedor: " + getLowestSale().getSalesmanName()
		).stream().collect(Collectors.joining("\r\n"));
	}	
}
