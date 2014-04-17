package com.bionic.socnet.ejb.logic;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.resource.ResourceException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.UploadedFile;
import org.xadisk.filesystem.exceptions.ClosedStreamException;
import org.xadisk.filesystem.exceptions.FileAlreadyExistsException;
import org.xadisk.filesystem.exceptions.FileNotExistsException;
import org.xadisk.filesystem.exceptions.FileUnderUseException;
import org.xadisk.filesystem.exceptions.InsufficientPermissionOnFileException;
import org.xadisk.filesystem.exceptions.LockingFailedException;
import org.xadisk.filesystem.exceptions.NoTransactionAssociatedException;

import com.bionic.socnet.ejb.XADiscEJB;
import com.bionic.socnet.ejb.dao.PhotoDAO;
import com.bionic.socnet.ejb.dao.UserDAO;
import com.bionic.socnet.entities.Album;
import com.bionic.socnet.entities.Conversation;
import com.bionic.socnet.entities.Photo;
import com.bionic.socnet.entities.Role;
import com.bionic.socnet.entities.User;

@Stateless
@LocalBean
public class RegistrationLogic {

	@EJB
	private UserDAO userDAO;
	@EJB
	private PhotoDAO photoDAO;

	@EJB
	XADiscEJB xaDiscEJB;

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public void createUser(User user, UploadedFile photo)
			throws Exception {
		user.setAvatarPath(addPhoto(user, photo));
		user.addRole(new Role(null, "user", null));
		user.setActive(true);
		String sb = generatePassword(user.getPrincipale().getPassword());
		user.getPrincipale().setPassword(sb.toString());
		try {
			userDAO.create(user);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"User with this login alredy exist", null));
			throw e;
		}
	}

	public String generatePassword(String password)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

	public void createPhoto(Album album, UploadedFile photo)
			throws FileAlreadyExistsException, FileNotExistsException,
			InsufficientPermissionOnFileException, LockingFailedException,
			NoTransactionAssociatedException, FileUnderUseException,
			ClosedStreamException, InterruptedException, IOException,
			ResourceException {
		
		Date date = new Date(System.currentTimeMillis());
		album.setDate(date);
		photoDAO.create(new Photo(date, null, addPhoto(album.getUser(), photo),
				album, new Conversation(date, null)));
	}

	public String addPhoto(User user, UploadedFile photo)
			throws FileAlreadyExistsException, FileNotExistsException,
			InsufficientPermissionOnFileException, LockingFailedException,
			NoTransactionAssociatedException, InterruptedException,
			IOException, FileUnderUseException, ResourceException,
			ClosedStreamException {
		
		String path = null;
		if (photo != null) {
			path = "" + System.currentTimeMillis();
			path = path + photo.getFileName();
			xaDiscEJB.write(path, photo);
		} else {
			path = "default.jpeg";
		}
		return path;
	}
}
