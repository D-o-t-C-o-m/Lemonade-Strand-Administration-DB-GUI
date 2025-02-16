package org.mike.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.mike.domain.Product;
import org.mike.exceptions.IDNotUniqueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mike.DAO.productDao;


public class ProductServer{
private static final Logger logger = LoggerFactory.getLogger(ProductServer.class);
private final productDao dao;

public ProductServer() throws IDNotUniqueException {
	this.dao = new productDao();
}

public Product save (Product product) {
	logger.info("Saving product{}", product);
	dao.saveProduct(product);
	return product;
}


public Product update(Product product) throws FileNotFoundException {
	logger.info("updating product{}", product);
	dao.saveProduct(product);
	return product;
}


public void delete(int entityId) throws FileNotFoundException {
	logger.info("deleting product{}", dao.getProduct(entityId));
	dao.deleteProduct(dao.getProduct(entityId));
}


public List<Product> findAll() {
	return dao.getAllProducts();
}

public Product findById(int entityId) {
	List<Product> products = findAll();
	for (Product product : products) {
		if (product.getId() == entityId) {
			return product;
		}

	}
	System.out.println("Not Found");
	return null;
}
}

