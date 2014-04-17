package com.bionic.socnet.beans;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.push.PushContextFactory;

import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.ejb.logic.MessageLogic;
import com.bionic.socnet.entities.User;

@ManagedBean
@ViewScoped
public class MessageBean {

	private User userTo;
	private String message;

	@Inject
	private MySession mySession;

	@EJB
	private MessageLogic messageLogic;

	public void viewMessage(User user) {
		mySession.setUserTo(user);
		if (!user.equals(mySession.getUser())) {
			mySession.setConversation(messageLogic.conversation(
					mySession.getUser(), user));
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("modal", true);
			options.put("draggable", false);
			options.put("resizable", false);
			options.put("contentHeight", 300);
			options.put("contentWidth", 400);
			RequestContext.getCurrentInstance().openDialog("send-message",
					options, null);
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"You can not send yourself a message", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void sendMessage() {
		if (!message.equals("") && message != null) {
			messageLogic.sendMessage(mySession.getUser(),
					mySession.getConversation(), message);
			RequestContext.getCurrentInstance().closeDialog(userTo);
			message = null;
		}
		
	}

	public void sendMessage(String message) {
		if (!message.equals("") && message != null) {
			messageLogic.sendMessage(mySession.getUser(),
					mySession.getConversation(), message);
			this.message = null;			
		}
	}
		

	public void onSend(SelectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Message sent", "");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public MySession getMySession() {
		return mySession;
	}

	public void setMySession(MySession mySession) {
		this.mySession = mySession;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
