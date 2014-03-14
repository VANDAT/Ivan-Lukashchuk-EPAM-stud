package com.epam.kiev.skipass.factories;

import com.epam.kiev.skipass.factories.cards.CountableSkiPass;
import com.epam.kiev.skipass.factories.cards.DaysSkiPass;
import com.epam.kiev.skipass.factories.cards.EveningSkiPass;
import com.epam.kiev.skipass.factories.cards.MorningSkiPass;
import com.epam.kiev.skipass.factories.cards.SkiPass;
import com.epam.kiev.skipass.factories.cards.weekdays.WorkDaysWrapper;
import com.epam.kiev.skipass.factories.enums.CountableSkiPassTypes;
import com.epam.kiev.skipass.factories.enums.DaysSkiPassTypes;

public class WorkDaysSkiPassFactory implements SkiPassFactory{
	
	@Override
	public SkiPass letOutCountSkiPass(CountableSkiPassTypes type) {		
		return new WorkDaysWrapper(new CountableSkiPass(type.getValue()));
	}

	@Override
	public SkiPass letOutDaysSkiPass(DaysSkiPassTypes type) {		
		return new WorkDaysWrapper(new DaysSkiPass(type.getValue()));
	}

	@Override
	public SkiPass letOutPartOfDaySkiPass(int partOfDay) {
		switch (partOfDay) {
		case SkiPassFactory.MORNING:
			return new WorkDaysWrapper(new MorningSkiPass());
		case SkiPassFactory.EVENING:
			return new WorkDaysWrapper(new EveningSkiPass());
		default:
			return null;
		}		
	}
	
	
}
