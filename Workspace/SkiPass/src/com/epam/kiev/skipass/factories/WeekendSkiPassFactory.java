package com.epam.kiev.skipass.factories;

import com.epam.kiev.skipass.factories.cards.CountableSkiPass;
import com.epam.kiev.skipass.factories.cards.DaysSkiPass;
import com.epam.kiev.skipass.factories.cards.EveningSkiPass;
import com.epam.kiev.skipass.factories.cards.MorningSkiPass;
import com.epam.kiev.skipass.factories.cards.SkiPass;
import com.epam.kiev.skipass.factories.cards.weekdays.WeekendWrapper;
import com.epam.kiev.skipass.factories.enums.CountableSkiPassTypes;
import com.epam.kiev.skipass.factories.enums.DaysSkiPassTypes;

public class WeekendSkiPassFactory implements SkiPassFactory{

	@Override
	public SkiPass letOutCountSkiPass(CountableSkiPassTypes type) {		
		return new WeekendWrapper(new CountableSkiPass(type.getValue()));
	}

	@Override
	public SkiPass letOutDaysSkiPass(DaysSkiPassTypes type) {		
		return new WeekendWrapper(new DaysSkiPass(type.getValue()));
	}

	@Override
	public SkiPass letOutPartOfDaySkiPass(int partOfDay) {
		switch (partOfDay) {
		case SkiPassFactory.MORNING:
			return new WeekendWrapper(new MorningSkiPass());
		case SkiPassFactory.EVENING:
			return new WeekendWrapper(new EveningSkiPass());
		default:
			return null;
		}		
	}		
}
