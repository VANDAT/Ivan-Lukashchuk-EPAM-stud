package com.epam.kiev.sinsum;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Double.*;

public class AtomicDouble extends Number {
	
	private static final long serialVersionUID = 7670515724557853826L;
	
	private AtomicLong bits;

	public AtomicDouble() {
		this(0.0);
	}
	
	 public final void add(double delta) {
	        for (;;) {
	            double current = get();
	            double next = current + delta;
	            if (compareAndSet(current, next))
	                return;
	        }
	    }

	public AtomicDouble(double initialValue) {
		bits = new AtomicLong(doubleToLongBits(initialValue));
	}
	
	public final boolean compareAndSet(double expect, double update) {
		return bits.compareAndSet(doubleToLongBits(expect),
				doubleToLongBits(update));
	}

	public final void set(double newValue) {
		bits.set(doubleToLongBits(newValue));
	}

	public final double get() {
		return longBitsToDouble(bits.get());
	}

	public double doubleValue() {
		return get();
	}

	public final double getAndSet(double newValue) {
		return longBitsToDouble(bits.getAndSet(doubleToLongBits(newValue)));
	}

	public final boolean weakCompareAndSet(double expect, double update) {
		return bits.weakCompareAndSet(doubleToLongBits(expect),
				doubleToLongBits(update));
	}

	public int intValue() {
		return (int) get();
	}

	public long longValue() {
		return (long) get();
	}
	
	public float floatValue() {		
		return (float) get();
	}
}
