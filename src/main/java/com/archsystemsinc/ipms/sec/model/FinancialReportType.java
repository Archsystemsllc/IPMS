/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.sec.model;

/**
* This is enum for the  Report Types.
* @author Benigna
* @version 0.2.1
*/
public enum FinancialReportType {
	/**
	 * Annual
	 */
	ANNUAL(12,"Annual"),
	/**
	 * SEMI ANUAL
	 */
	SEMI_ANNUAL(6,"Semi-Annual"),
	/**
	 * QUARTERLY
	 */
	QUARTERLY(3,"Quarterly"),
	/**
	 * 
	 */
	MONTHLY(1,"Monthly");
	
	/**
	 * The Report Type Key
	 */
	private final int key;
	
	/**
	 * The Report Type Value
	 */
    private final String value;

    /**
     * Constructs a FinancialReportType
     * @param key - The Report Key
     * @param value
     */
    FinancialReportType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the Key for the report type
     * @return the report type key
     */
    public int getKey() {
        return key;
    }
    /**
     * Returns the value for the report type
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * 
     * @param key The Key for the Report Type
     * @return <code>FinancialReportType</code>
     */
	public static FinancialReportType getFinancialReportType(int key) {
		for(FinancialReportType type:values()) {
			if(type.getKey() == key)
				return type;
		}
		return null;
	}
}
