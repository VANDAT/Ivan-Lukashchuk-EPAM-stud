package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.Conversation;
import com.bionic.socnet.entities.User;

@Stateless
public class ConversationDAO extends AbstractDAO<Conversation, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public ConversationDAO() {
		super(Conversation.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Conversation> findByUser(User user) {
		try {
			TypedQuery<Conversation> typedQuery = em.createNamedQuery(
					"Conversation.findAllByUser", Conversation.class);
			typedQuery.setParameter("user", user);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String findUnreadCount(User user) {
		try {
			Query query = em.createQuery("SELECT COUNT(cu) FROM ConversationUser cu WHERE cu.user = :user AND cu.read = false");
			query.setParameter("user", user);
			return query.getSingleResult().toString();
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	public Conversation findPublicConversationByUser(User user) {
		try {
			TypedQuery<Conversation> typedQuery = em.createNamedQuery(
					"ConversationUser.findPublicConversationByUser", Conversation.class);
			typedQuery.setParameter("title", "publicUser" + user.getId());
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
}
