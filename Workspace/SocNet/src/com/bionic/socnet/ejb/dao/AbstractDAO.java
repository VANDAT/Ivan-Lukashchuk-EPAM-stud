package com.bionic.socnet.ejb.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

public abstract class AbstractDAO<T, PK extends Serializable> {
	 private Class<T> entityClass;

	    public AbstractDAO(Class<T> entityClass) {
	        this.entityClass = entityClass;
	    }

	    protected abstract EntityManager getEntityManager();

	    public void create(T entity) {
	        getEntityManager().persist(entity);
	    }

	    public void edit(T entity) {
	        getEntityManager().merge(entity);
	    }

	    public void remove(T entity) {
	        getEntityManager().remove(getEntityManager().merge(entity));
	    }

	    public T find(PK id) {
	        return getEntityManager().find(entityClass, id);
	    }

	}