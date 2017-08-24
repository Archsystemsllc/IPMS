package com.archsystemsinc.ipms.sec.model;

public enum UserActivityType {
	DELETE("DELETE"),
	UPDATE("UPDATE"),
	CREATE("CREATE");
	
	private String type;

	UserActivityType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
