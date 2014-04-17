package com.bionic.socnet.beans;

import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.ejb.dao.UserDAO;
import com.bionic.socnet.ejb.logic.RegistrationLogic;
import com.bionic.socnet.entities.User;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	@EJB
	private UserDAO userDAO;
	@EJB
	private RegistrationLogic registrationLogic;
	
	@Inject 
	private MySession mySession;
	
	private String login;
	private String password;
	private User user;
	private String userIdentifier;
	
	public HttpServletRequest getRequest() { 
		  return (HttpServletRequest) FacesContext.getCurrentInstance() 
				    .getExternalContext().getRequest();  
		    } 
	
	public String signIn() throws NoSuchAlgorithmException{
		HttpServletRequest request = getRequest();			
		try {
	        if (request.getUserPrincipal() != null) {
	            request.logout();
	        }
			request.login(login, registrationLogic.generatePassword(password));
			User user = userDAO.findByLogin(login);
			mySession.setUser(user);
			this.user = user;
			if (!user.isActive()){
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"You are locked", null));
				return null;
			}
			
		} catch (ServletException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Wrong login or password", null));
			e.printStackTrace();
			return null;			
		}
		return "faces/main?faces-redirect=true";	
	}
	
	public String logout(){
		HttpServletRequest request = getRequest();
		request.getSession().invalidate();
		return "/login?faces-redirect=true";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public MySession getMySession() {
		return mySession;
	}

	public void setMySession(MySession mySession) {
		this.mySession = mySession;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUserIdentifier() {
		System.out.println("bfdb");			
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

}
