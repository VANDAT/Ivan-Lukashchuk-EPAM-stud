package com.epam.kiev.skipass;

import java.util.Date;

public abstract class UncountableSkiPass extends AbstractSkiPass {
	
	private Date activateDate;

	public Date getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}
			
}
