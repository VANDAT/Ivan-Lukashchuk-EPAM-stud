package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the friend database table.
 * 
 */
@Entity @Table(name = "friend")
@NamedQueries({
		@NamedQuery(name = "Friend.findAll", query = "SELECT f FROM Friend f"),
		@NamedQuery(name = "Friend.findFriendship", query = "SELECT f FROM Friend f WHERE f.friend = :friend AND f.user= :user OR f.friend = :user AND f.user= :friend"),
		@NamedQuery(name = "Friend.findInvitationsToFriends", query = "SELECT f FROM Friend f WHERE f.friend= :user AND f.status = 'invite' "), 
		@NamedQuery(name = "Friend.removeFriendship", query = "DELETE FROM Friend f WHERE f.id = :id"), 
		@NamedQuery(name = "Friend.findUsersFriends", query = "SELECT f FROM Friend f WHERE (f.user= :user OR f.friend= :user) AND f.status = 'friend' "),
		@NamedQuery(name = "Friend.findUsersAsFriends", query = "SELECT f FROM Friend f WHERE f.user= :user OR f.friend= :user")
})
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "friend_id")
	private User friend;

	public Friend() {
	}

	public Friend(String status, User friend) {
		super();
		this.status = status;
		this.friend = friend;
	}

	public Friend(Long id, String status, User user, User friend) {
		super();
		this.id = id;
		this.status = status;
		this.user = user;
		this.friend = friend;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return this.friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friend == null) ? 0 : friend.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Friend))
			return false;
		Friend other = (Friend) obj;
		if (friend == null) {
			if (other.friend != null)
				return false;
		} else if (!friend.equals(other.friend))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Friend [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", user=");
		builder.append(user);
		builder.append(", friend=");
		builder.append(friend);
		builder.append("]");
		return builder.toString();
	}

}