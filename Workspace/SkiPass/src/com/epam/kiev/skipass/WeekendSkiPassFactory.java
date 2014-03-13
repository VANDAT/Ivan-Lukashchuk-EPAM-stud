package com.epam.kiev.skipass;

import com.epam.kiev.skipass.pass.CountableSkiPass;
import com.epam.kiev.skipass.pass.DaysSkiPass;
import com.epam.kiev.skipass.pass.EveningSkiPass;
import com.epam.kiev.skipass.pass.MorningSkiPass;
import com.epam.kiev.skipass.pass.SkiPass;
import com.epam.kiev.skipass.pass.weekdays.WeekendWrapper;

public class WeekendSkiPassFactory implements SkiPassFactory{

	@Override
	public SkiPass letOutCountSkiPass(int numberOfLifts) {		
		return new WeekendWrapper(new CountableSkiPass(numberOfLifts));
	}

	@Override
	public SkiPass letOutDaysSkiPass(int numberOfDays) {		
		return new WeekendWrapper(new DaysSkiPass(numberOfDays));
	}

	@Override
	public SkiPass lrtOutPartOfDaySkiPass(int partOfDay) {
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
