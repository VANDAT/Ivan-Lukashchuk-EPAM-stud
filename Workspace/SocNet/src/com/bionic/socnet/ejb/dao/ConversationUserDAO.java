package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.ConversationUser;
import com.bionic.socnet.entities.User;

@Stateless
public class ConversationUserDAO extends AbstractDAO<ConversationUser, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public ConversationUserDAO() {
		super(ConversationUser.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<ConversationUser> findByUser(User user) {
		try {
			TypedQuery<ConversationUser> typedQuery = em.createNamedQuery(
					"ConversationUser.findByUser", ConversationUser.class);
			typedQuery.setParameter("user", user);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ConversationUser findSingleConversationByUser(User userFrom,
			User userTo) {
		try {
			TypedQuery<ConversationUser> typedQuery = em.createNamedQuery(
					"ConversationUser.findSingleConversationByUser",
					ConversationUser.class);
			typedQuery.setParameter("user", userFrom);
			typedQuery.setParameter("userTo", userTo);
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

	}

}
