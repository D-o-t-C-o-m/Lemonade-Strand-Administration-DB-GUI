package org.mike.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Entity;

import java.util.List;

public class DAO <T extends Entity> {

private final Class<T> entityClass;

public DAO(Class<T> entityClass) {
	this.entityClass = entityClass;
}

public void save(T entity) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	session.persist(entity);
	tx.commit();
	session.close();
}

public void update(T entity) {
	Transaction tx = null;
	try (Session session = HibernateUtil.getSession()) {
		tx = session.beginTransaction();
		session.merge(entity);
		tx.commit();
	} catch (Exception e) {
		if (tx != null) tx.rollback();
		throw e;
	}
}

public void delete(T entity) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	session.remove(entity);
	tx.commit();
	session.close();
}

public T get(int id) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	T entity = session.get(entityClass, id);
	tx.commit();
	session.close();
	return entity;
}

public List<T> getAll() {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	List<T> entities = session.createQuery("from " + entityClass.getName(), entityClass).getResultList();
	tx.commit();
	session.close();
	return entities;
}
public void preloadCache() {
	try (Session session = HibernateUtil.getSession()) {
		Transaction tx = session.beginTransaction();
		session.createQuery(
						"FROM " + entityClass.getName(), entityClass)
				.setCacheable(true)
				.getResultList();
		tx.commit();
	}
}
}