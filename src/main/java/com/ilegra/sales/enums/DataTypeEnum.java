package com.ilegra.sales.enums;

public enum DataTypeEnum {

	SALESMAN("001"),
	CLIENT("002"),
	SALE("003");

	private String code;

	DataTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String toString() {
		return code;
	}
}
