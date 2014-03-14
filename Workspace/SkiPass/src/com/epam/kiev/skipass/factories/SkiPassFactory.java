package com.epam.kiev.skipass.factories;

import com.epam.kiev.skipass.factories.cards.SkiPass;
import com.epam.kiev.skipass.factories.enums.CountableSkiPassTypes;
import com.epam.kiev.skipass.factories.enums.DaysSkiPassTypes;

public interface SkiPassFactory {
	
	int MORNING = 1;
	int EVENING = 2;
	
	SkiPass letOutCountSkiPass(CountableSkiPassTypes type);
	SkiPass letOutDaysSkiPass(DaysSkiPassTypes type);
	SkiPass letOutPartOfDaySkiPass(int partOfDay);
}
