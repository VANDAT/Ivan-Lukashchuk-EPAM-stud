package com.epam.kiev.generic;

import java.util.Collection;

public class ArrayToCollection {
	
	public <T> void copyArrayToCollection(T[] a, Collection<T> c){
		for(T t : a){
			c.add(t);
		}
	}
}
