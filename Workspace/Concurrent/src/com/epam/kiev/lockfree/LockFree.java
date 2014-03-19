package com.epam.kiev.lockfree;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockFree {

	private static class State {
		final BigInteger curr;

		public State(BigInteger curr) {
			this.curr = curr;
		}
	}

	private final AtomicReference<State> atomicState = new AtomicReference<>(
			new State(BigInteger.ONE));

	public BigInteger next() {
		BigInteger value = null;
		while (true) {
			State state = atomicState.get();
			value = state.curr;
			State newState = new State(state.curr.add(state.curr));
			if (atomicState.compareAndSet(state, newState)) {
				break;
			}
		}
		return value;
	}

}
