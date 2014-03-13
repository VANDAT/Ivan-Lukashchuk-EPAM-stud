package com.epam.kiev.skipass.adminapi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.epam.kiev.skipass.factories.cards.SkiPass;

public class History {

	private static Map<Class<? extends SkiPass>, AtomicLong> countLiftsMap = new HashMap<Class<? extends SkiPass>, AtomicLong>();
	private static Map<Class<? extends SkiPass>, AtomicLong> countDeniedLiftsMap = new HashMap<Class<? extends SkiPass>, AtomicLong>();

	private Class<? extends SkiPass> clazz;

	static {
		countLiftsMap.put(null, new AtomicLong(0));
		countDeniedLiftsMap.put(null, new AtomicLong(0));
	}

	public History(Class<? extends SkiPass> clazz) {
		countLiftsMap.put(clazz, new AtomicLong(0));
		countDeniedLiftsMap.put(clazz, new AtomicLong(0));
	}

	public void count(boolean isValid) {
		if (isValid) {
			countLiftsMap.get(clazz).incrementAndGet();
			countLiftsMap.get(null).incrementAndGet();
		} else {
			countDeniedLiftsMap.get(clazz).incrementAndGet();
			countDeniedLiftsMap.get(null).incrementAndGet();
		}
	}

	public static long getCountLifts(Class<? extends SkiPass> clazz) {
		return countLiftsMap.get(clazz).get();
	}

	public static long getCountDeniedLifts(Class<? extends SkiPass> clazz) {
		return countDeniedLiftsMap.get(clazz).get();
	}
}
