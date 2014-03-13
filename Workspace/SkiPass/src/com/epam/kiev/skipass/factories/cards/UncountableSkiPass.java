package com.epam.kiev.skipass.factories.cards;

import java.util.Date;

public abstract class UncountableSkiPass extends AbstractSkiPass {
		
	private static final long serialVersionUID = -8206169881838500783L;
	
	protected Date activationDate;
	protected Date validUntil;
	
	public UncountableSkiPass(){
		activationDate = new Date();
	}
	
	@Override
	public boolean isValid() {
		return super.isValid() && validUntil.after(new Date());
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}		
}
