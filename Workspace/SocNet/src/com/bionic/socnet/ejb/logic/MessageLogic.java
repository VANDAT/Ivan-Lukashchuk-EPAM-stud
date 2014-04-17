package com.bionic.socnet.ejb.logic;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.richfaces.application.push.MessageException;

import com.bionic.socnet.beans.PushBean;
import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.ejb.dao.ConversationDAO;
import com.bionic.socnet.ejb.dao.ConversationUserDAO;
import com.bionic.socnet.ejb.dao.MessageDAO;
import com.bionic.socnet.entities.Conversation;
import com.bionic.socnet.entities.ConversationUser;
import com.bionic.socnet.entities.Message;
import com.bionic.socnet.entities.User;

@Stateless
@LocalBean
public class MessageLogic {

	@EJB
	private ConversationUserDAO conversationUserDAO;
	@EJB
	private MessageDAO messageDAO;
	@EJB
	private ConversationDAO conversationDAO;
	@Inject
	private MySession mySession;
	@Inject PushBean pushBean;

	public Conversation conversation(User user, User userTo){
		Date currentTime = new Date(System.currentTimeMillis());
		ConversationUser conversationUser = conversationUserDAO
				.findSingleConversationByUser(user, userTo);
		Conversation conversation;
		if (conversationUser == null) {
			conversation = new Conversation(currentTime, null);
			conversationDAO.create(conversation);
			conversationUser = new ConversationUser(null, true,
					conversation, user);
			conversationUserDAO.create(conversationUser);
			conversationUserDAO.create(new ConversationUser(null, false,
					conversation, userTo));			
		}
		conversation = conversationUser.getConversation();
		conversation.setDate(currentTime);
		conversationDAO.edit(conversation);
		return conversation;
	}
	
	public void sendMessage(User user, Conversation conversation, String message) {
		Date currentTime = new Date(System.currentTimeMillis());		
		messageDAO.create(new Message(currentTime, message, conversation, user));
		conversation.setDate(currentTime);
		for(ConversationUser conversationUser : conversation.getConversationUsers()){
			if(conversationUser.getUser().equals(user)){
				conversationUser.setRead(true);
			}else{
				conversationUser.setRead(false);
			}
		}
		conversationDAO.edit(conversation);
		try {
			System.out.println("111");
			pushBean.sendMessage();
			System.out.println("222");
		} catch (MessageException e) {			
			e.printStackTrace();
		}
	}

	public void createConversation(Conversation conversation, Message message,
			List<User> usersTo) {
		User user = mySession.getUser();
		Date currentTime = new Date(System.currentTimeMillis());
		conversation.setDate(currentTime);
		conversationDAO.create(conversation);
		message.setDate(currentTime);
		message.setConversation(conversation);
		message.setUser(user);
		messageDAO.create(message);
		conversationUserDAO.create(new ConversationUser(null, true, conversation, user));
		for(User userTo : usersTo){
			conversationUserDAO.create(new ConversationUser(null, false, conversation, userTo));
		}
	}
}
