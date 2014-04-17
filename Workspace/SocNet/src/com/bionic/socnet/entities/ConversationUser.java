package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the conversation_users database table.
 * 
 */
@Entity
@Table(name = "conversation_users")
@NamedQueries({
		@NamedQuery(name = "ConversationUser.findAll", query = "SELECT cu FROM ConversationUser cu"),
		@NamedQuery(name = "ConversationUser.findByUser", query = "SELECT cu FROM ConversationUser cu WHERE cu.user = :user"),
		@NamedQuery(name = "ConversationUser.findSingleConversationByUser", query = "SELECT cu FROM ConversationUser cu WHERE cu.user = :userTo AND cu.conversation IN "
				+ "(SELECT cu1.conversation FROM ConversationUser cu1 WHERE cu1.user = :user AND cu1.conversation.title = null)") })
public class ConversationUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "was_read")
	private Boolean read;

	@ManyToOne(fetch = FetchType.LAZY)
	private Conversation conversation;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	public ConversationUser() {
	}

	public ConversationUser(Long id, Boolean read, Conversation conversation,
			User user) {
		super();
		this.id = id;
		this.read = read;
		this.conversation = conversation;
		this.user = user;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getRead() {
		return this.read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Conversation getConversation() {
		return this.conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
		result = prime * result
				+ ((conversation == null) ? 0 : conversation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((read == null) ? 0 : read.hashCode());
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
		if (!(obj instanceof ConversationUser))
			return false;
		ConversationUser other = (ConversationUser) obj;
		if (conversation == null) {
			if (other.conversation != null)
				return false;
		} else if (!conversation.equals(other.conversation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (read == null) {
			if (other.read != null)
				return false;
		} else if (!read.equals(other.read))
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
		builder.append("ConversationUser [id=");
		builder.append(id);
		builder.append(", read=");
		builder.append(read);
		builder.append(", conversation=");
		builder.append(conversation);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}