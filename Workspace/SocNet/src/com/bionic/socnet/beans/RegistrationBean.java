package com.bionic.socnet.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;

import com.bionic.socnet.ejb.dao.UserDAO;
import com.bionic.socnet.ejb.logic.RegistrationLogic;
import com.bionic.socnet.entities.Principale;
import com.bionic.socnet.entities.User;

@ManagedBean
@ViewScoped
public class RegistrationBean {

	@EJB
	private RegistrationLogic registrationLogic;
	@EJB
	private UserDAO userDAO;
	private User user = new User();
	private UploadedFile photo;

	private CroppedImage croppedImage;

	{
		user.setPrincipale(new Principale());
	}

	public void loginCheck(FacesContext context, UIComponent component,
			Object value) {
		if (value.toString().length() < 3) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"", "Login must be at least 3 characters");
			throw new ValidatorException(message);
		} else if (userDAO.findByLogin(value.toString()) != null) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"", "User with this login already exists");
			throw new ValidatorException(message);

		}
	}

	public void img() {
		try {
			InputStream inputStream = photo.getInputstream();
			OutputStream outputStream = FacesContext.getCurrentInstance()
					.getExternalContext().getResponseOutputStream();
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = inputStream.read(buf)) >= 0) {
				outputStream.write(buf, 0, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		photo = event.getFile();
		FacesMessage msg = new FacesMessage("Succesful", photo.getFileName()
				+ " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String registration() {
		try {
			registrationLogic.createUser(user, photo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"You are registrated", null));
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UploadedFile getPhoto() {
		return photo;
	}

	public void setPhoto(UploadedFile photo) {
		this.photo = photo;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

}
