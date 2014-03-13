package com.epam.kiev.skipass.pass;

import java.util.Date;

public abstract class UncountableSkiPass extends AbstractSkiPass {
	
	protected Date activateDate;
	protected Date validUntil;
	
	public UncountableSkiPass(){
		activateDate = new Date();
	}
		
	@Override
	public boolean lift() {		
		return isValid();
	}
	
	@Override
	public boolean isValid() {
		return super.isValid() && validUntil.after(new Date());
	}
	
	public Date getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}			
}
