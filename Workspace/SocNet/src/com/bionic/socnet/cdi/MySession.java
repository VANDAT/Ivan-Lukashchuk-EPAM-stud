package com.bionic.socnet.cdi;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.resource.ResourceException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.xadisk.filesystem.exceptions.ClosedStreamException;
import org.xadisk.filesystem.exceptions.FileAlreadyExistsException;
import org.xadisk.filesystem.exceptions.FileNotExistsException;
import org.xadisk.filesystem.exceptions.FileUnderUseException;
import org.xadisk.filesystem.exceptions.InsufficientPermissionOnFileException;
import org.xadisk.filesystem.exceptions.LockingFailedException;
import org.xadisk.filesystem.exceptions.NoTransactionAssociatedException;

import com.bionic.socnet.ejb.dao.ConversationDAO;
import com.bionic.socnet.ejb.dao.FriendDAO;
import com.bionic.socnet.ejb.dao.UserDAO;
import com.bionic.socnet.ejb.logic.RegistrationLogic;
import com.bionic.socnet.entities.Album;
import com.bionic.socnet.entities.Conversation;
import com.bionic.socnet.entities.ConversationUser;
import com.bionic.socnet.entities.Photo;
import com.bionic.socnet.entities.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Default
@SessionScoped
@ManagedBean
public class MySession implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;
	@EJB
	private RegistrationLogic registrationLogic;
	@EJB
	private ConversationDAO conversationDAO;
	@EJB
	private FriendDAO friendDAO;

	private User user;
	private User userTo;
	private Conversation conversation;
	private Album album;
	private User selectedUser;
	private String locale = "English";
	private Photo photo;
	private List<User> friends;
	private UploadedFile avatar;
	private List<Photo> photos;
	private String channel;
	
	public String showCountNewFriends(){
		String count = friendDAO.findNewFriendCount(user);
		if(count.equals("0")){
			count = null;
		}else{
			count = " + " + count;
		}
		return count;
	}
	
	public String showCountUnreadConversations(){
		String count = conversationDAO.findUnreadCount(user);
		if(count.equals("0")){
			count = null;
		}else{
			count = " + " + count;
		}
		return count;
	}

	public void handleFileUpload(FileUploadEvent event) {
		avatar = event.getFile();
		FacesMessage msg = new FacesMessage("Succesful", avatar.getFileName()
				+ " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String editProfile() throws FileAlreadyExistsException,
			FileNotExistsException, InsufficientPermissionOnFileException,
			LockingFailedException, NoTransactionAssociatedException,
			FileUnderUseException, ClosedStreamException, InterruptedException,
			IOException, ResourceException {
		if (avatar != null) {
			user.setAvatarPath(registrationLogic.addPhoto(user, avatar));
		}
		userDAO.edit(user);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Your profile edited", ""));
		return "main?faces-redirect=ture";
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public void changeLocale(String locale) {
		if (locale.equals("ru")) {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(new Locale("ru"));
			this.locale = "Русский";
		} else if (locale.equals("en")) {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(new Locale("en"));
			this.locale = "English";
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		StringBuilder sb = new StringBuilder();
		sb.append("/");
		for(ConversationUser conversationUser : conversation.getConversationUsers()){
			sb.append(conversationUser.getUser().getId());
			sb.append("S");
		}
		if(sb.length() > 1){
			channel = sb.toString();			
//			RequestContext.getCurrentInstance().execute("subscriber.connect('/" + channel + "')");  
		}
		this.conversation = conversation;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
