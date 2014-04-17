package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.Album;
import com.bionic.socnet.entities.User;

@Stateless
public class AlbumDAO extends AbstractDAO<Album, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public AlbumDAO() {
		super(Album.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Album> findByUser(User user) {
		try {
			TypedQuery<Album> typedQuery = em.createNamedQuery(
					"Album.findByUser", Album.class);
			typedQuery.setParameter("user", user);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
