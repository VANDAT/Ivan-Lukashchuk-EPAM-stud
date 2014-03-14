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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (blocked ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSkiPass other = (AbstractSkiPass) obj;
		if (blocked != other.blocked)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
