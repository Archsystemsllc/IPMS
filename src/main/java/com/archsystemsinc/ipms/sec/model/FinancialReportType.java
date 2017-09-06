package com.archsystemsinc.ipms.sec.model;

public enum FinancialReportType {

	ANNUAL(12,"Annual"), 
	SEMI_ANNUAL(6,"Semi-Annual"),
	QUARTERLY(3,"Quarterly"),
	MONTHLY(1,"Monthly");
	
	private final int key;
    private final String value;

    FinancialReportType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    
    
	public static FinancialReportType getFinancialReportType(int key) {
		for(FinancialReportType type:values()) {
			if(type.getKey() == key)
				return type;
		}
		return null;
	}
}
