package com.epam.kiev.skipass.pass;

import java.util.Date;

public abstract class UncountableSkiPass extends AbstractSkiPass {
	
	protected Date activateDate;
	
	public UncountableSkiPass(){
		activateDate = new Date();
	}
	
	@Override
	public boolean lift() {		
		return isValid();
	}
	
	public Date getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}			
}
