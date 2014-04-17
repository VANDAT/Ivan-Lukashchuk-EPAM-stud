package com.epam.kiev.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fruit {
	static void copyAll(Collection<? extends Fruit> collection,
			Collection<? extends Fruit> out) {

	}

	static <E extends Fruit & Comparable<E>> void copy(
			Collection<E> collection,  E elem) {
		Collection<E> out = new ArrayList<>();
		for(E e : collection){
			if(e.compareTo(elem)>0){
				out.add(e);
			}
		}
	}

	public static void main(String[] args) {
		List<Fruit> fr = null;
		List<Apple> app = null;
		List<Orange> or = null;
		List<RedApple> rapp = null;
		List<Melon> mel = null;
		copyAll(or, fr);
		copyAll(rapp, fr);
		copyAll(rapp, app);
		copyAll(rapp, rapp);
		copyAll(fr, or);

	}

}

class Apple extends Fruit implements Comparable<Apple> {

	@Override
	public int compareTo(Apple o) {
		return 0;
	}
}

class RedApple extends Apple {
}

class Orange extends Fruit {
}

class Melon {
}
