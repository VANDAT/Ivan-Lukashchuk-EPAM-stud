package com.epam.kiev.skipass.adminapi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.epam.kiev.skipass.factories.cards.SeasonSkiPass;
import com.epam.kiev.skipass.factories.cards.SkiPass;

public class History {

	private static Map<Class<? extends SkiPass>, AtomicLong> countLiftsMap = new HashMap<Class<? extends SkiPass>, AtomicLong>();
	private static Map<Class<? extends SkiPass>, AtomicLong> countDeniedLiftsMap = new HashMap<Class<? extends SkiPass>, AtomicLong>();

	private Class<? extends SkiPass> clazz;

	public History(Class<? extends SkiPass> clazz) {
		this.clazz = clazz;
		countLiftsMap.put(clazz, new AtomicLong(0));
		countDeniedLiftsMap.put(clazz, new AtomicLong(0));
	}

	public void count(boolean isValid) {
		if (isValid) {
			countLiftsMap.get(clazz).incrementAndGet();
		} else {
			countDeniedLiftsMap.get(clazz).incrementAndGet();
		}
	}

	public static long getCountLifts(Class<? extends SkiPass> clazz) {
		return countLiftsMap.get(clazz).get();
	}

	public static long getCountDeniedLifts(Class<? extends SkiPass> clazz) {
		return countDeniedLiftsMap.get(clazz).get();
	}

	public static String viewCountLifts() {
		long sum = getSumOfCount(countLiftsMap);
		try {
			sum += countLiftsMap.get(SeasonSkiPass.class).get();
		} catch (NullPointerException e) {
		}
		return "Count of lifts = " + sum / 2;
	}

	public static String viewCountDeniedLifts() {
		return "Count of denied lifts = " + getSumOfCount(countDeniedLiftsMap);
	}

	private static long getSumOfCount(
			Map<Class<? extends SkiPass>, AtomicLong> countMap) {
		long sum = 0;
		for (Class<? extends SkiPass> clazz : countMap.keySet()) {
			sum += countMap.get(clazz).get();
		}
		return sum;
	}

	public static String viewCountLiftsByTypes() {
		return "Count of lifts by types:{ \n" + viewCountByType(countLiftsMap)
				+ "}";
	}

	private static String viewCountByType(
			Map<Class<? extends SkiPass>, AtomicLong> countMap) {
		StringBuilder sb = new StringBuilder();
		for (Class<? extends SkiPass> clazz : countMap.keySet()) {
			sb.append(clazz.getSimpleName()).append(" = ")
					.append(countMap.get(clazz).get()).append("\n");
		}
		return sb.toString();
	}

	public static String viewCountDeniedLiftsByTypes() {
		return "Count of denied lifts by types:{\n "
				+ viewCountByType(countDeniedLiftsMap) + "}";
	}
}
