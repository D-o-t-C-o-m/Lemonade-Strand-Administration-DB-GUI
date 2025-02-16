package org.mike.repository;

import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;

import java.io.FileNotFoundException;

public interface RepositoryInterface<T> {
	T save(T entity) throws IDNotUniqueException, ValidationException, FileNotFoundException;
	T update(T entity) throws FileNotFoundException;
	void delete(int entityId) throws FileNotFoundException;
	Iterable<T> findAll();
	T findById(int entityId);
}
