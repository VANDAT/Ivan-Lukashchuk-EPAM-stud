package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity @Table(name = "photo")
@NamedQueries({
	@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p"),
	@NamedQuery(name="Photo.findByAlbum", query="SELECT p FROM Photo p WHERE p.album = :album")
})

public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String description;

	private String path;

	@ManyToOne(fetch=FetchType.LAZY)
	private Album album;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name="convetsation_id")
	private Conversation conversation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="photo")
	private List<Vote> votes = new ArrayList<Vote>();

	public Photo() {
	}

	public Photo(Date date, String description, String path, Album album,
			Conversation conversation) {
		super();
		this.date = date;
		this.description = description;
		this.path = path;
		this.album = album;
		this.conversation = conversation;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Conversation getConversation() {
		return this.conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public List<Vote> getVotes() {
		return this.votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Vote addVote(Vote vote) {
		getVotes().add(vote);
		vote.setPhoto(this);

		return vote;
	}

	public Vote removeVote(Vote vote) {
		getVotes().remove(vote);
		vote.setPhoto(null);

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
				+ ((conversation == null) ? 0 : conversation.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		if (!(obj instanceof Photo))
			return false;
		Photo other = (Photo) obj;
		if (conversation == null) {
			if (other.conversation != null)
				return false;
		} else if (!conversation.equals(other.conversation))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Photo [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", description=");
		builder.append(description);
		builder.append(", path=");
		builder.append(path);
		builder.append(", conversation=");
		builder.append(conversation);
		builder.append("]");
		return builder.toString();
	}

}