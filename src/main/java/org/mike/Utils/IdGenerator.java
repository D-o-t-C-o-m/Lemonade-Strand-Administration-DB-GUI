package org.mike.Utils;

import org.mike.DAO.DAO;
import org.mike.domain.Entity;

import java.util.List;
import java.util.Random;

public class IdGenerator<T extends Entity> {

private final Random random = new Random();
private final DAO<T> dao;

public IdGenerator(DAO<T> dao) {
	this.dao = dao;
}

public int generateId() {
	int id = random.nextInt(999);
	List<T> entities = dao.getAll();
	int finalId = id;
	while (entities.stream().anyMatch(e -> e.getId() == finalId)) {
		id = random.nextInt(999);
	}
	return id;
}
}