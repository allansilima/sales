package com.ilegra.sales.service;

import com.ilegra.sales.model.Sale;

public interface SalesSummary {
	public Long countClient();

	public Long countSalesman();

	public Sale getBiggestSale();

	public Sale getLowestSale();
}
