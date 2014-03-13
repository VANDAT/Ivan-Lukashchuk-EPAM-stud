package com.epam.kiev.skipass.factories;

import com.epam.kiev.skipass.factories.cards.SkiPass;

public interface SkiPassFactory {
	
	int MORNING = 1;
	int EVENING = 2;
	
	SkiPass letOutCountSkiPass(int numberOfLifts);
	SkiPass letOutDaysSkiPass(int numberOfDays);
	SkiPass letOutPartOfDaySkiPass(int partOfDay);
}
