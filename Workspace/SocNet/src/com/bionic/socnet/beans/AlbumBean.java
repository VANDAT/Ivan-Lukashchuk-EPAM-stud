package com.bionic.socnet.beans;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.resource.ResourceException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.xadisk.filesystem.exceptions.ClosedStreamException;
import org.xadisk.filesystem.exceptions.FileAlreadyExistsException;
import org.xadisk.filesystem.exceptions.FileNotExistsException;
import org.xadisk.filesystem.exceptions.FileUnderUseException;
import org.xadisk.filesystem.exceptions.InsufficientPermissionOnFileException;
import org.xadisk.filesystem.exceptions.LockingFailedException;
import org.xadisk.filesystem.exceptions.NoTransactionAssociatedException;

import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.ejb.dao.AlbumDAO;
import com.bionic.socnet.ejb.dao.PhotoDAO;
import com.bionic.socnet.ejb.dao.VoteDAO;
import com.bionic.socnet.ejb.logic.RegistrationLogic;
import com.bionic.socnet.entities.Album;
import com.bionic.socnet.entities.Photo;
import com.bionic.socnet.entities.User;
import com.bionic.socnet.entities.Vote;

@ManagedBean
@ViewScoped
public class AlbumBean {

	@EJB
	private AlbumDAO albumDAO;
	@EJB
	private PhotoDAO photoDAO;
	@EJB
	private RegistrationLogic registrationLogic;
	@EJB
	private VoteDAO voteDAO;
	@Inject
	MySession mySession;

	private String title;
	private String description;

	private Album album;
	private Vote vote = new Vote();
	private List<Vote> votes;
	private int rating;
	
	public Photo findFirstPhoto(Album album){
		Photo photo = photoDAO.findByAlbumFirst(album);
		if(photo == null){
			photo = new Photo();
			photo.setPath("empty.jpeg");
		}
		return photo;
	}

	public void showNextPhoto(Photo photo) {
		List<Photo> photos = mySession.getPhotos();
		ListIterator<Photo> listIterator = photos.listIterator(photos
				.indexOf(photo));
		listIterator.next();
		if (listIterator.hasNext()) {
			mySession.setPhoto(listIterator.next());
		} else {
			mySession.setPhoto(photos.get(0));
		}
		mySession.setConversation(mySession.getPhoto().getConversation());
	}

	public int getRating() {
		votes = voteDAO.findByPhoto(mySession.getPhoto());
		rating = 0;
		if (votes != null && votes.size() != 0) {
			for (Vote vote : votes) {
				rating += vote.getLevel();
			}
			rating = rating / votes.size();
		}
		return rating;
	}

	public void onRate() {
		if (votes != null) {
			for (Vote vote : votes) {
				if (vote.getUser().equals(mySession.getUser())) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"You already voted", null));
					return;
				}
			}
		}
		voteDAO.create(new Vote(rating, mySession.getUser(), mySession
				.getPhoto()));
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void showPhoto(Photo photo) {
		mySession.setConversation(photo.getConversation());
		mySession.setPhoto(photo);
		setVotes(voteDAO.findByPhoto(photo));
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 600);
		RequestContext.getCurrentInstance().openDialog("photo", options, null);
	}

	public List<Album> findAlbums(User user) {	
		return albumDAO.findByUser(user);
	}
	
	public String showAlbums(User user) {		
		mySession.setSelectedUser(user);
		return "users-albums?faces-redirect=true";
	}
	

	public void handleFileUpload(FileUploadEvent event)
			throws FileAlreadyExistsException, FileNotExistsException,
			InsufficientPermissionOnFileException, LockingFailedException,
			NoTransactionAssociatedException, FileUnderUseException,
			ClosedStreamException, InterruptedException, IOException,
			ResourceException {
		registrationLogic.createPhoto(mySession.getAlbum(), event.getFile());
		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String showAlbum(Album album) {
		mySession.setAlbum(album);
		return "photos?faces-redirect=true";
	}

	public List<Photo> findPhotos() {
		mySession.setPhotos(photoDAO.findByAlbum(mySession.getAlbum()));
		return mySession.getPhotos();
	}

	public List<Photo> showPhotos() {
		mySession.setPhotos(photoDAO.findByAlbum(mySession.getAlbum()));
		return mySession.getPhotos();
	}

	public void addAlbum() {
		albumDAO.create(new Album(title, description, mySession.getUser(),
				new Date(System.currentTimeMillis())));
		RequestContext.getCurrentInstance().closeDialog("");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Album added", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void showAddAlbum() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 300);
		options.put("contentWidth", 400);
		RequestContext.getCurrentInstance().openDialog("add-album", options,
				null);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MySession getMySession() {
		return mySession;
	}

	public void setMySession(MySession mySession) {
		this.mySession = mySession;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		mySession.setAlbum(album);
		this.album = album;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

}
