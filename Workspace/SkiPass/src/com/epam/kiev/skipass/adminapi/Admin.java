package com.epam.kiev.skipass.adminapi;

import com.epam.kiev.skipass.factories.SkiPassAbstractFactory;
import com.epam.kiev.skipass.factories.SkiPassFactory;
import com.epam.kiev.skipass.factories.cards.SkiPass;

public class Admin {

	private SkiPassFactory weekendSkiPassFactory = SkiPassAbstractFactory
			.getSkiPassFactory(SkiPassAbstractFactory.WEEKEND_TYPE);
	private SkiPassFactory workDaysSkiPassFactory = SkiPassAbstractFactory
			.getSkiPassFactory(SkiPassAbstractFactory.WORK_DAYS_TYPE);

	public SkiPass letOut1DayWeekendSkiPass() {
		return weekendSkiPassFactory.letOutDaysSkiPass(1);
	}

	public SkiPass letOut2DaysWeekendSkiPass() {
		return weekendSkiPassFactory.letOutDaysSkiPass(2);
	}

	public SkiPass letOut5LiftsWeekendSkiPass() {
		return weekendSkiPassFactory.letOutCountSkiPass(5);
	}

	public SkiPass letOut10LiftsWeekendSkiPass() {
		return weekendSkiPassFactory.letOutCountSkiPass(10);
	}
		
	public SkiPass letOut1DayWorkDaysSkiPass() {
		return workDaysSkiPassFactory.letOutDaysSkiPass(1);
	}

	public SkiPass letOut2DaysWorkDaysSkiPass() {
		return workDaysSkiPassFactory.letOutDaysSkiPass(2);
	}
	
	public SkiPass letOut5DaysWorkDaysSkiPass() {
		return workDaysSkiPassFactory.letOutDaysSkiPass(5);
	}

	public SkiPass letOut5LiftsWorkDaysSkiPass() {
		return workDaysSkiPassFactory.letOutCountSkiPass(5);
	}

	public SkiPass letOut10LiftsWorkDaysSkiPass() {
		return workDaysSkiPassFactory.letOutCountSkiPass(10);
	}
	
	public SkiPass letOutSeasonSkiPass(){
		return SkiPassAbstractFactory.letOutSessonSkiPass();
	}
}
