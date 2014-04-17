package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.Album;
import com.bionic.socnet.entities.Photo;

@Stateless
public class PhotoDAO extends AbstractDAO<Photo, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public PhotoDAO() {
		super(Photo.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Photo> findByAlbum(Album album) {
		try {
			TypedQuery<Photo> typedQuery = em.createNamedQuery(
					"Photo.findByAlbum", Photo.class);
			typedQuery.setParameter("album", album);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Photo findByAlbumFirst(Album album) {
		try {
			TypedQuery<Photo> typedQuery = em.createNamedQuery(
					"Photo.findByAlbum", Photo.class);
			typedQuery.setParameter("album", album);
			typedQuery.setFirstResult(0);
			typedQuery.setMaxResults(1);
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
