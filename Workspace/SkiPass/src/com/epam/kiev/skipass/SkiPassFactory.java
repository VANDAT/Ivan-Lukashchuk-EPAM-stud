package com.epam.kiev.skipass;

import com.epam.kiev.skipass.pass.SkiPass;

public interface SkiPassFactory {
	
	//TO DO constants
	
	SkiPass letOutCountSkiPass(int numberOfLifts);
	SkiPass letOutDaysSkiPass(int numberOfDays);
	SkiPass lrtOutPartOfDaySkiPass(int partOfDay);
}
