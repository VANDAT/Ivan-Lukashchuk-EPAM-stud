package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.Conversation;
import com.bionic.socnet.entities.Message;

@Stateless
public class MessageDAO extends AbstractDAO<Message, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public MessageDAO() {
		super(Message.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Message> findByConversation(Conversation conversation) {
		try {
			TypedQuery<Message> typedQuery = em.createNamedQuery(
					"Message.findByConversation", Message.class);
			typedQuery.setParameter("conversation", conversation);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Message> findByConversationLimit(Conversation conversation,
			int first, int size) {
		try {
			TypedQuery<Message> typedQuery = em.createNamedQuery(
					"Message.findByConversation", Message.class);
			typedQuery.setParameter("conversation", conversation);
			typedQuery.setFirstResult(first);
			typedQuery.setMaxResults(size);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
