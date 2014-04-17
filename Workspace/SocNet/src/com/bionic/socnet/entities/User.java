package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity @Table(name = "users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
    @NamedQuery(name="User.findByName", query="SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name="User.findByLogin", query="SELECT u FROM User u WHERE u.principale.login = :login"),
	@NamedQuery(name="User.findByNamePattern", query="SELECT u FROM User u WHERE u.name LIKE :name"),
    @NamedQuery(name="User.findFriends1", query="SELECT f.user FROM Friend f WHERE f.friend = :user AND f.status = 'friend'"),
    @NamedQuery(name="User.findFriends2", query="SELECT f.friend FROM Friend f WHERE f.user = :user AND f.status = 'friend'")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="avatar_path")
	private String avatarPath;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String email;

	private String name;

	private String surname;
	
	private boolean active;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<Album> albums = new ArrayList<Album>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<ConversationUser> conversationUsers = new ArrayList<ConversationUser>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private List<Friend> friends = new ArrayList<Friend>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private List<Role> roles = new ArrayList<Role>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<Vote> votes = new ArrayList<Vote>();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "principale_id")
	private Principale principale;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatarPath() {
		return this.avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setUser(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setUser(null);

		return album;
	}

	public List<ConversationUser> getConversationUsers() {
		return this.conversationUsers;
	}

	public void setConversationUsers(List<ConversationUser> conversationUsers) {
		this.conversationUsers = conversationUsers;
	}

	public ConversationUser addConversationUser(ConversationUser conversationUser) {
		getConversationUsers().add(conversationUser);
		conversationUser.setUser(this);

		return conversationUser;
	}

	public ConversationUser removeConversationUser(ConversationUser conversationUser) {
		getConversationUsers().remove(conversationUser);
		conversationUser.setUser(null);

		return conversationUser;
	}

	public List<Friend> getFriends() {
		return this.friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public Friend addFriend(Friend friend) {
		getFriends().add(friend);
		friend.setUser(this);

		return friend;
	}

	public Friend removeFriend(Friend friend) {
		getFriends().remove(friend);
		friend.setUser(null);

		return friend;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setUser(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setUser(null);

		return role;
	}

	public List<Vote> getVotes() {
		return this.votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Vote addVote(Vote vote) {
		getVotes().add(vote);
		vote.setUser(this);

		return vote;
	}

	public Vote removeVote(Vote vote) {
		getVotes().remove(vote);
		vote.setUser(null);

		return vote;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((avatarPath == null) ? 0 : avatarPath.hashCode());
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (avatarPath == null) {
			if (other.avatarPath != null)
				return false;
		} else if (!avatarPath.equals(other.avatarPath))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", avatarPath=");
		builder.append(avatarPath);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", email=");
		builder.append(email);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append("]");
		return builder.toString();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Principale getPrincipale() {
		return principale;
	}

	public void setPrincipale(Principale principale) {
		this.principale = principale;
	}
	
}