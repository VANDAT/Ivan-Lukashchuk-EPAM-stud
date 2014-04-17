package com.bionic.socnet.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.ejb.dao.ConversationDAO;
import com.bionic.socnet.ejb.dao.ConversationUserDAO;
import com.bionic.socnet.ejb.dao.MessageDAO;
import com.bionic.socnet.ejb.logic.MessageLogic;
import com.bionic.socnet.entities.Conversation;
import com.bionic.socnet.entities.ConversationUser;
import com.bionic.socnet.entities.Message;
import com.bionic.socnet.entities.User;

@ManagedBean
@ViewScoped
public class ConversationBean {

	@EJB
	private MessageLogic messageLogic;
	@EJB
	private MessageDAO messageDAO;
	@EJB
	private ConversationDAO conversationDAO;
	@EJB
	private ConversationUserDAO conversationUserDAO;
	@Inject
	private FriendsBean friendsBean;
	@Inject
	private MySession mySession;

	private Message message = new Message();

	private Conversation conversation = new Conversation();

	private List<Message> messages;

	private DualListModel<User> users;

	private List<User> recipients;
	
	private List<Conversation> conversations;

	@PostConstruct
	public void init() {
		List<User> friends = friendsBean.getFriends();
		mySession.setFriends(friends);
		users = new DualListModel<User>(friends, new ArrayList<User>());
	}
	
	public void clear(){
		messages = null;
	}
	
	
	public boolean isRead(Conversation conversation){
		User user = mySession.getUser();
		for(ConversationUser conversationUser : conversation.getConversationUsers()){
			if(conversationUser.getUser().equals(user)){
				return conversationUser.getRead();
			}
		}
		return true;
	}
	
	public String showCountUnreadConversations(){
		String count = conversationDAO.findUnreadCount(mySession.getUser());
		if(count.equals("0")){
			count = null;
		}else{
			count = " + " + count;
		}
		return count;
	}

	public List<User> getRecipients() {
		if (recipients == null) {
			recipients = new ArrayList<User>();
			for (ConversationUser conversationUser : mySession
					.getConversation().getConversationUsers()) {
				User user = conversationUser.getUser();
				if (!user.equals(mySession.getUser())) {
					recipients.add(user);
				}
			}
		}
		return recipients;
	}

	public void createConversation() {
		List<User> usersTo = users.getTarget();
		if (usersTo.size() > 0) {
			messageLogic.createConversation(conversation, message,
					usersTo);
			conversation = new Conversation();
			message = new Message();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Message is sent", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public String showTitle(Conversation conversation) {
		String title = conversation.getTitle();
		if (title == null) {
			User user = conversation.getConversationUsers().get(0).getUser();
			if (user.equals(mySession.getUser())) {
				title = conversation.getConversationUsers().get(1).getUser()
						.getName();
			} else {
				title = user.getName();
			}
		}
		return title;
	}

	public List<Message> showMessages() {
		List<Message> mess = messageDAO.findByConversationLimit(
				mySession.getConversation(), 0, 5);
		try {
			if (messages == null) {
				messages = mess;
			} else {
				int i;
				for (i = 0; i < mess.size()
						&& !messages.get(0).equals(mess.get(i)); i++) {
				}
				messages.addAll(0, mess.subList(0, i));
			}
		} catch (IndexOutOfBoundsException e) {
			messages = mess;
		}
		List<Message> result = new ArrayList<Message>(messages);
		Collections.reverse(result);
		return result;
	}
	
	public List<Message> showMessages(User user){
		Conversation conversation = conversationDAO.findPublicConversationByUser(user);
		if(conversation == null){
			conversation = new Conversation(new Date(System.currentTimeMillis()), "publicUser" + user.getId());
			conversationDAO.create(conversation);
		}
		mySession.setConversation(conversation);
		System.out.println(conversation);
		return showMessages();
	}
	
	

	public void showMoreMessages() {
		messages.addAll(messageDAO.findByConversationLimit(
				mySession.getConversation(), messages.size(), 5));
	}

	public String showConversation(Conversation conversation) {
		User user = mySession.getUser();
		List<ConversationUser> conversationUsers = conversation.getConversationUsers();
		for(ConversationUser conversationUser : conversationUsers){
			if(conversationUser.getUser().equals(user)){
				conversationUser.setRead(true);
				conversationUserDAO.edit(conversationUser);
				break;
			}
		}
		mySession.setConversation(conversation);
		return "messages?faces-redirect=true";
	}

	public List<Conversation> showConversations() {
		
		return conversationDAO.findByUser(mySession.getUser());
	}

	public DualListModel<User> getUsers() {
		return users;
	}

	public void setUsers(DualListModel<User> users) {
		this.users = users;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

}