package com.crystal.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

public class Enumerator implements Enumeration {

	private Set<String> set;
	private Iterator<String> iterator;
	
	public Enumerator(Set<String> set) {
		this.set = set;
		iterator = set.iterator();
	}
	
	@Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	@Override
	public Object nextElement() {
		return iterator.next();
	}

}
