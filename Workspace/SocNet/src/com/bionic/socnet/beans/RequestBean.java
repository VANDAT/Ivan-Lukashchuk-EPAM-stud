package com.bionic.socnet.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;


@ManagedBean
@ViewScoped
public class RequestBean {
	
	@Inject
	private SearchBean searchBean;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		searchBean.setName(name);
		this.name = name;
	}
	
	
	
}
