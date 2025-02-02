package org.mike.service;

import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.repository.GenericRepository;
import org.mike.validators.SupplierValidator;

import java.io.FileNotFoundException;

public class SupplierService {
private SupplierValidator supplierValidator;
private GenericRepository<Supplier> supplierRepository;

public SupplierService(GenericRepository<Supplier> supplierRepository) {
	this.supplierRepository = supplierRepository;
	this.supplierValidator = new SupplierValidator();
}

public Supplier saveSupplier(int id, String name, String email) throws ValidationException, IDNotUniqueException, FileNotFoundException {
	Supplier supplier = new Supplier(id, name, email);
	supplierValidator.validateSupplier(supplier);

	return supplierRepository.save(supplier);
}

public void deleteSupplier(int id) throws FileNotFoundException {
	this.supplierRepository.delete(id);
}

public Supplier updateSupplier(int id, String name, String email) throws FileNotFoundException {
	Supplier supplierToUpdate = new Supplier(id, name, email);
	return this.supplierRepository.update(supplierToUpdate);
}

public Iterable<Supplier> findAll() {
	return this.supplierRepository.findAll();
}

public Supplier findById(int id) {
	return this.supplierRepository.findById(id);
}
}
