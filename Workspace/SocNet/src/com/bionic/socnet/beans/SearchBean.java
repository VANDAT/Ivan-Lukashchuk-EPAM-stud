package com.bionic.socnet.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.ejb.dao.FriendDAO;
import com.bionic.socnet.ejb.dao.UserDAO;
import com.bionic.socnet.entities.Friend;
import com.bionic.socnet.entities.User;

@Default
@ManagedBean
@SessionScoped
public class SearchBean {

	@EJB
	private UserDAO userDAO;
	@EJB
	private FriendDAO friendDAO;

	private LazyDataModel<User> dataModel;

	@Inject
	private MySession mySession;

	private User selectedUser;
	
	public String selectedUser(User user){
		selectedUser = user;
		return "page?faces-redirect=true";
	}
	
	private String name;
	List<User> data = new ArrayList<User>();
	

	public void showPage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler()
				.handleNavigation(facesContext, null, "page.xhtml?faces-redirect=true");
	}

	public String search() {
		dataModel = new LazyDataModel<User>() {

			private static final long serialVersionUID = 1L;

			{
				this.setRowCount(userDAO.findByNameCount(name));
			}

			@Override
			public List<User> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {
				data = userDAO.findByNameLimit(name, first, pageSize);
				return data;
			}
		};
		return "search?faces-redirect=true";
	}

	public void addToFriends(User friend) {
		FacesMessage message;
		User user = mySession.getUser();
		if (!friend.equals(mySession.getUser())) {
			if (friendDAO.findFriendship(user, friend) == null) {
				friendDAO.create(new Friend(null, "invite", user, friend));
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Invite to friend:", "You invited " + friend.getName());
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Invite to friend:", friend.getName()
								+ " is your friend");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"You can not add yourself to friends", "");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public boolean isFriend(User user){
		if(user.equals(
				mySession.getUser())){
			return true;
		}
		Friend friend = friendDAO.findFriendship(mySession.getUser(), user);		
		return friend != null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LazyDataModel<User> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<User> dataModel) {
		this.dataModel = dataModel;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
