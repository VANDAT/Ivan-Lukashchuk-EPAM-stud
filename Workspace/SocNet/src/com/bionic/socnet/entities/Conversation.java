package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the conversation database table.
 * 
 */
@Entity
@Table(name = "conversation")
@NamedQueries({
		@NamedQuery(name = "Conversation.findAll", query = "SELECT c FROM Conversation c"),
		@NamedQuery(name = "Conversation.findAllByUser", query = "SELECT cu.conversation FROM ConversationUser cu WHERE cu.user = :user ORDER BY cu.conversation.date DESC"),
		@NamedQuery(name = "ConversationUser.findPublicConversationByUser", query = "SELECT c FROM Conversation c WHERE c.title = :title") })
public class Conversation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String title;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "conversation", cascade = CascadeType.MERGE)
	private List<ConversationUser> conversationUsers = new ArrayList<ConversationUser>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conversation")
	private List<Message> messages = new ArrayList<Message>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conversation")
	private List<Photo> photos = new ArrayList<Photo>();

	public Conversation() {
	}

	public Conversation(Date date, String title) {
		super();
		this.date = date;
		this.title = title;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ConversationUser> getConversationUsers() {
		return this.conversationUsers;
	}

	public void setConversationUsers(List<ConversationUser> conversationUsers) {
		this.conversationUsers = conversationUsers;
	}

	public ConversationUser addConversationUser(
			ConversationUser conversationUser) {
		getConversationUsers().add(conversationUser);
		conversationUser.setConversation(this);

		return conversationUser;
	}

	public ConversationUser removeConversationUser(
			ConversationUser conversationUser) {
		getConversationUsers().remove(conversationUser);
		conversationUser.setConversation(null);

		return conversationUser;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setConversation(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setConversation(null);

		return message;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setConversation(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setConversation(null);

		return photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Conversation))
			return false;
		Conversation other = (Conversation) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conversation [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}