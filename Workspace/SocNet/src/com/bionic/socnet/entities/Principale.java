package com.bionic.socnet.entities;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the album database table.
 * 
 */
@Entity @Table(name = "principale")
public class Principale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String login;
	private String password;
	
	public Principale() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}