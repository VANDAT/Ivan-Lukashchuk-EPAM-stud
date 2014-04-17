package com.bionic.socnet.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.bionic.socnet.cdi.MySession;

@ManagedBean
@SessionScoped
public class SessionBean {
	
	@Inject
	private MySession mySession;

	public MySession getMySession() {
		return mySession;
	}

	public void setMySession(MySession mySession) {
		this.mySession = mySession;
	}
	
}
