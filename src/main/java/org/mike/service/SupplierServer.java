package org.mike.service;

import org.mike.DAO.DAO;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.validators.SupplierValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupplierServer extends GenericServer<Supplier> {
private static final Logger logger = LoggerFactory.getLogger(SupplierServer.class);
private final SupplierValidator supplierValidator = new SupplierValidator();

public SupplierServer() {
	super(new DAO<>(Supplier.class));
}

@Override
public Supplier save(Supplier supplier) throws ValidationException, IDNotUniqueException {
	supplierValidator.validateSupplier(supplier);
	logger.info("Saving supplier {}", supplier);
	super.save(supplier);
	return supplier;
}
}