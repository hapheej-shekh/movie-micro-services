package com.starter.jpa.service;

public enum Type {
	Temporary("Temporary"), Permanent("Permanent");
	
	private final String type;
	
	private Type(String type) {
		this.type = type;
	}
	
	public String getType() {
		
		return this.type;
	}
}
