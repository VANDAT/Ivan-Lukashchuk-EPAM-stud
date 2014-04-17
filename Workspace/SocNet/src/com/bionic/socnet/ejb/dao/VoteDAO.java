package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.Photo;
import com.bionic.socnet.entities.Vote;

@Stateless
public class VoteDAO extends AbstractDAO<Vote, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public VoteDAO() {
		super(Vote.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Vote> findByPhoto(Photo photo) {
		try {
			TypedQuery<Vote> typedQuery = em.createNamedQuery(
					"Vote.findByPhoto", Vote.class);
			typedQuery.setParameter("photo", photo);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
