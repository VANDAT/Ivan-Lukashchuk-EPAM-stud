package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.Friend;
import com.bionic.socnet.entities.User;

@Stateless
public class FriendDAO extends AbstractDAO<Friend, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public FriendDAO() {
		super(Friend.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

		
	public List<Friend> findUsersFriends(User user) {
		try {
			TypedQuery<Friend> typedQuery = em.createNamedQuery(
					"Friend.findUsersFriends", Friend.class);
			typedQuery.setParameter("user", user);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Friend> findInvitationsToFriends(User user) {
		try {
			TypedQuery<Friend> typedQuery = em.createNamedQuery(
					"Friend.findInvitationsToFriends", Friend.class);
			typedQuery.setParameter("user", user);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Friend findFriendship(User user, User friend) {
		try {
			TypedQuery<Friend> typedQuery = em.createNamedQuery(
					"Friend.findFriendship", Friend.class);
			typedQuery.setParameter("user", user);
			typedQuery.setParameter("friend", friend);
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void removeFriendship(Long id) {
			Query query = em.createNamedQuery(
					"Friend.removeFriendship");
			query.setParameter("id", id);
			query.executeUpdate();
	}
	
	public String findNewFriendCount(User user) {
		try {
			Query query = em.createQuery("SELECT COUNT(f) FROM Friend f WHERE f.friend = :user AND f.status = 'invite'");
			query.setParameter("user", user);
			return query.getSingleResult().toString();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public String findFriendsCount(User user) {
		try {
			Query query = em.createQuery("SELECT COUNT(f) FROM Friend f WHERE (f.user= :user OR f.friend= :user) AND f.status = 'friend' ");
			query.setParameter("user", user);
			return query.getSingleResult().toString();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	
}
