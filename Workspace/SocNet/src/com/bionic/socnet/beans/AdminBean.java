package com.bionic.socnet.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bionic.socnet.ejb.dao.UserDAO;
import com.bionic.socnet.entities.User;

@ManagedBean
@ViewScoped
public class AdminBean {
	
	@EJB
	private UserDAO userDAO;
	
	public void lockUser(User user){
		user.setActive(false);
		userDAO.edit(user);
	}
	
	public void unlockUser(User user){
		user.setActive(true);
		userDAO.edit(user);
	}
	
	
}
