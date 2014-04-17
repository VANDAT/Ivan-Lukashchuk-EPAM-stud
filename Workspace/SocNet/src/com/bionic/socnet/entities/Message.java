package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity @Table(name = "message")
@NamedQueries({
	@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m"),
	@NamedQuery(name="Message.findByConversation", query="SELECT m FROM Message m WHERE m.conversation = :conversation ORDER BY m.date DESC" )
})

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String message;

	@ManyToOne(fetch=FetchType.LAZY)
	private Conversation conversation;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	public Message() {
	}
		
	public Message(Date date, String message, Conversation conversation,
			User user) {
		super();
		this.date = date;
		this.message = message;
		this.conversation = conversation;
		this.user = user;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Conversation getConversation() {
		return this.conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conversation == null) ? 0 : conversation.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
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
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}