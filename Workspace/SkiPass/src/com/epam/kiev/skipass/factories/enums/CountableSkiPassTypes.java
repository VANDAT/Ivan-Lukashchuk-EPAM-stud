package com.epam.kiev.skipass.factories.enums;

public enum CountableSkiPassTypes {
	LIFTS_10(10), LIFTS_20(20), LIFTS_50(50), LIFTS_100(100);
	
	private int value;
	
	CountableSkiPassTypes(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
