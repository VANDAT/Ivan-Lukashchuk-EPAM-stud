package com.epam.kiev.skipass;

public abstract class AbstractSkiPass implements SkiPass{

	private long id;
	private boolean bloked;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isBloked() {
		return bloked;
	}

	public void setBloked(boolean bloked) {
		this.bloked = bloked;
	}
		
}
