package com.epam.kiev.skipass.adminapi;

import com.epam.kiev.skipass.factories.SkiPassAbstractFactory;
import com.epam.kiev.skipass.factories.SkiPassFactory;
import com.epam.kiev.skipass.factories.cards.SkiPass;
import com.epam.kiev.skipass.factories.enums.CountableSkiPassTypes;
import com.epam.kiev.skipass.factories.enums.DaysSkiPassTypes;

public class Admin {

	private SkiPassFactory weekendSkiPassFactory = SkiPassAbstractFactory
			.getSkiPassFactory(SkiPassAbstractFactory.WEEKEND_TYPE);
	private SkiPassFactory workDaysSkiPassFactory = SkiPassAbstractFactory
			.getSkiPassFactory(SkiPassAbstractFactory.WORK_DAYS_TYPE);

	public SkiPass letOut1DayWeekendSkiPass() {
		return weekendSkiPassFactory.letOutDaysSkiPass(DaysSkiPassTypes.DAYS_1);
	}

	public SkiPass letOut2DaysWeekendSkiPass() {
		return weekendSkiPassFactory.letOutDaysSkiPass(DaysSkiPassTypes.DAYS_2);
	}

	public SkiPass letOut10LiftsWeekendSkiPass() {
		return weekendSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_10);
	}

	public SkiPass letOut20LiftsWeekendSkiPass() {
		return weekendSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_20);
	}

	public SkiPass letOut50LiftsWeekendSkiPass() {
		return weekendSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_50);
	}

	public SkiPass letOut100LiftsWeekendSkiPass() {
		return weekendSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_100);
	}

	public SkiPass letOut1DayWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutDaysSkiPass(DaysSkiPassTypes.DAYS_1);
	}

	public SkiPass letOut2DaysWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutDaysSkiPass(DaysSkiPassTypes.DAYS_2);
	}

	public SkiPass letOut5DaysWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutDaysSkiPass(DaysSkiPassTypes.DAYS_5);
	}

	public SkiPass letOut10LiftsWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_10);
	}

	public SkiPass letOut20LiftsWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_20);
	}

	public SkiPass letOut50LiftsWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_50);
	}

	public SkiPass letOut100LiftsWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutCountSkiPass(CountableSkiPassTypes.LIFTS_100);
	}

	public SkiPass letOutMorningWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutPartOfDaySkiPass(SkiPassFactory.MORNING);
	}

	public SkiPass letOutEveningWorkDaysSkiPass() {
		return workDaysSkiPassFactory
				.letOutPartOfDaySkiPass(SkiPassFactory.EVENING);
	}
	
	public SkiPass letOutMorningWeekendSkiPass() {
		return weekendSkiPassFactory
				.letOutPartOfDaySkiPass(SkiPassFactory.MORNING);
	}

	public SkiPass letOutEveningWeekendSkiPass() {
		return weekendSkiPassFactory
				.letOutPartOfDaySkiPass(SkiPassFactory.EVENING);
	}

	public SkiPass letOutSeasonSkiPass() {
		return SkiPassAbstractFactory.letOutSessonSkiPass();
	}
}
