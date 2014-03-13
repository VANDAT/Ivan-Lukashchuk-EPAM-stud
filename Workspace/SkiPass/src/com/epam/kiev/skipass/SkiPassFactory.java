package com.epam.kiev.skipass;

import com.epam.kiev.skipass.pass.SkiPass;

public interface SkiPassFactory {
	
	int MORNING = 1;
	int EVENING = 2;
	
	SkiPass letOutCountSkiPass(int numberOfLifts);
	SkiPass letOutDaysSkiPass(int numberOfDays);
	SkiPass lrtOutPartOfDaySkiPass(int partOfDay);
}
