package com.bionic.socnet.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.ejb.dao.FriendDAO;
import com.bionic.socnet.ejb.dao.UserDAO;
import com.bionic.socnet.entities.Friend;
import com.bionic.socnet.entities.User;

@ManagedBean
@ViewScoped
public class FriendsBean {

	@EJB
	private FriendDAO friendDAO;
	@EJB
	private UserDAO userDAO;

	@Inject
	private MySession mySession;

	private List<Friend> friends;

	public MySession getMySession() {
		return mySession;
	}

	public List<User> showFriends(User user) {
		return userDAO.findFriendsLimit(user, 0, 9);
	}

	public void remove(User friend) {
		User user = mySession.getUser();
		mySession.getFriends().remove(friend);

		for (Friend f : friends) {
			if ((f.getUser().equals(user) && f.getFriend().equals(friend))
					|| f.getUser().equals(friend) && f.getFriend().equals(user)) {
				friendDAO.removeFriendship(f.getId());
			}
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Remove", "Friend " + friend.getName() + " removed!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void setMySession(MySession mySession) {
		this.mySession = mySession;
	}

	public List<User> getFriends() {
		List<User> users = mySession.getFriends();
		if (users == null) {
			friends = friendDAO.findUsersFriends(mySession.getUser());
			Long userId = mySession.getUser().getId();
			users = new ArrayList<User>();
			for (Friend friend : friends) {
				if (friend.getUser().getId().equals(userId)) {
					users.add(friend.getFriend());
				} else {
					users.add(friend.getUser());
				}
			}
			mySession.setFriends(users);
		}

		return users;
	}

	public List<Friend> getInvitationsToFriends() {
		return friendDAO.findInvitationsToFriends(mySession.getUser());
	}

	public void confirm(Friend friend) {
		List<User> users = mySession.getFriends();
		friend.setStatus("friend");
		friendDAO.edit(friend);
		users.add(friend.getUser());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Friend", "Friendship whith " + friend.getUser().getName()
						+ " confirmed!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
