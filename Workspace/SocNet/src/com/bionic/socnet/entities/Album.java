package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity @Table(name = "album")
@NamedQueries({
	@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a"),
	@NamedQuery(name="Album.findByUser", query="SELECT a FROM Album a WHERE a.user = :user")
})

public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="album")
	private List<Photo> photos = new ArrayList<Photo>();

	public Album() {
	}

	public Album(String title, String description, User user, Date date) {
		super();
		this.title = title;
		this.description = description;
		this.user = user;
		this.date = date;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setAlbum(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setAlbum(null);

		return photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (!(obj instanceof Album))
			return false;
		Album other = (Album) obj;
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
		builder.append("Album [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}