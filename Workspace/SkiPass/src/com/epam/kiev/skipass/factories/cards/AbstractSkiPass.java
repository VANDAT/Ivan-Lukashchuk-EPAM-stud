package com.epam.kiev.skipass.factories.cards;

import java.util.UUID;

public abstract class AbstractSkiPass implements SkiPass{
	
	private static final long serialVersionUID = 3269862699333734924L;
	
	private long id;
	private boolean blocked;
	
	public AbstractSkiPass(){
		id = UUID.randomUUID().getLeastSignificantBits();
	}
	
	@Override
	public boolean lift() {	
		boolean isValid = isValid();
		historyCount(isValid);
		return isValid;
	}
	
	public boolean isValid(){
		return !blocked;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	protected abstract void historyCount(boolean isValid);
}
