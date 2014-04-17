package com.bionic.socnet.ejb.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bionic.socnet.entities.User;

@Stateless
@LocalBean
public class UserDAO extends AbstractDAO<User, Long> {

	@PersistenceContext(unitName = "soc_net_db")
	EntityManager em;

	public UserDAO() {
		super(User.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Integer findByNameCount(String name) {
		try {
			name = name + "%'";
			Query query = em
					.createQuery("SELECT COUNT(u) FROM User u WHERE u.name LIKE '"
							+ name);
			return new Integer("" + query.getSingleResult());
		} catch (NoResultException e) {
			return 0;
		}
	}

	public User findByLogin(String login) {
		try {
			TypedQuery<User> typedQuery = em.createNamedQuery(
					"User.findByLogin", User.class);
			typedQuery.setParameter("login", login);
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> findByName(String name) {
		try {
			TypedQuery<User> typedQuery = em.createNamedQuery(
					"User.findByName", User.class);
			typedQuery.setParameter("name", name);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> findByNameLimit(String name, int first, int size) {
		try {
			TypedQuery<User> typedQuery = em.createNamedQuery(
					"User.findByNamePattern", User.class);
			name = name + "%";
			typedQuery.setParameter("name", name);
			typedQuery.setFirstResult(first);
			typedQuery.setMaxResults(size);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> findByNamePattern(String namePattern) {
		try {
			namePattern = namePattern + "%";
			TypedQuery<User> typedQuery = em.createNamedQuery(
					"User.findByNamePattern", User.class);
			typedQuery.setParameter("name", namePattern);
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> findFriends(User user) {
		List<User> friends;
		try {
			TypedQuery<User> typedQuery = em.createNamedQuery(
					"User.findFriends1", User.class);
			typedQuery.setParameter("user", user);
			friends = typedQuery.getResultList();
			typedQuery = em.createNamedQuery("User.findFriends2", User.class);
			typedQuery.setParameter("user", user);
			friends.addAll(typedQuery.getResultList());
			return friends;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> findFriendsLimit(User user, int first, int size) {
		List<User> friends;
		try {
			TypedQuery<User> typedQuery = em.createNamedQuery(
					"User.findFriends1", User.class);
			typedQuery.setParameter("user", user);
			typedQuery.setFirstResult(first);
			typedQuery.setMaxResults(size);
			friends = typedQuery.getResultList();
			System.out.println(friends);			
			size -= friends.size();;
			System.out.println(first);
			System.out.println(size);
			if (friends.isEmpty()) {
				first = 0;
			}
			if (size != 0){
				typedQuery = em.createNamedQuery("User.findFriends2",
						User.class);
				typedQuery.setParameter("user", user);
				typedQuery.setFirstResult(first);
				typedQuery.setMaxResults(size);
				friends.addAll(typedQuery.getResultList());
			}
			return friends;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
