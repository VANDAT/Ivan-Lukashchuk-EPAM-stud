package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the vote database table.
 * 
 */
@Entity @Table(name = "vote")
@NamedQueries({
	@NamedQuery(name="Vote.findAll", query="SELECT v FROM Vote v"),
	@NamedQuery(name="Vote.findByPhoto", query="SELECT v FROM Vote v WHERE v.photo = :photo")
})
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int level;

	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	@ManyToOne(fetch=FetchType.LAZY)
	private Photo photo;

	public Vote() {
	}
			
	public Vote(int level, User user, Photo photo) {
		super();
		this.level = level;
		this.user = user;
		this.photo = photo;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}