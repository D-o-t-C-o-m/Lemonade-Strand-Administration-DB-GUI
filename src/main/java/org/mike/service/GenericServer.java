package org.mike.service;

import java.util.List;
import org.mike.DAO.DAO;
import org.mike.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericServer<T extends Entity> {
private static final Logger logger = LoggerFactory.getLogger(GenericServer.class);
private final DAO<T> dao;

public GenericServer(DAO<T> dao) {
	this.dao = dao;
}

public T save(T entity) {
	logger.info("Saving entity {}", entity);
	dao.save(entity);
	return entity;
}

public T update(T entity) {
	logger.info("Updating entity {}", entity);
	dao.update(entity);
	return entity;
}

public void delete(int entityId) {
	logger.info("Deleting entity {}", dao.get(entityId));
	dao.delete(dao.get(entityId));
}

public List<T> findAll() {
	return dao.getAll();
}

public T findById(int entityId) {
	List<T> entities = findAll();
	for (T entity : entities) {
		if (entity.getId() == entityId) {
			return entity;
		}
	}
	System.out.println("Not Found");
	return null;
}

public Iterable<T> getAll() {
	return dao.getAll();
}
}