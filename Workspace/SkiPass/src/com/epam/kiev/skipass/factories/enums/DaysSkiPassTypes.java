package com.epam.kiev.skipass.factories.enums;

public enum DaysSkiPassTypes {
	DAYS_1(1), DAYS_2(2), DAYS_5(5);
	
	private int value;
	
	DaysSkiPassTypes(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
