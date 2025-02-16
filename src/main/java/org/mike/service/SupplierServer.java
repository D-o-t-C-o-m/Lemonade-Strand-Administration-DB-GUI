package org.mike.service;

import org.mike.domain.Product;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.validators.SupplierValidator;
import java.util.List;
import org.mike.exceptions.IDNotUniqueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mike.DAO.supplierDAO;


public class SupplierServer {
private final SupplierValidator supplierValidator = new SupplierValidator();
private static final Logger logger = LoggerFactory.getLogger(SupplierServer.class);
private final supplierDAO dao;


public SupplierServer() {
	this.dao = new supplierDAO();
}

	public Supplier save(Supplier supplier) throws ValidationException, IDNotUniqueException {
		logger.info("Saving supplier{}", supplier);
		dao.saveSupplier(supplier);
		return supplier;
	}

	public Supplier update(Supplier supplier){
		logger.info("updating supplier{}", supplier);
		dao.saveSupplier(supplier);
		return supplier;
		}
	public void delete ( int entityId){
		logger.info("deleting supplier{}", dao.getSupplier(entityId));
		dao.deleteProduct(dao.getSupplier(entityId));
		}

	public List<Supplier> findAll () {
		return dao.getAllSuppliers();
		}

	public Supplier findById ( int id){
		List<Supplier> suppliers = findAll();
		for (Supplier supplier : suppliers) {
			if (supplier.getId() == id) {
				return supplier;
				}

			}
		System.out.println("Not Found");
			return null;
		}
	}
