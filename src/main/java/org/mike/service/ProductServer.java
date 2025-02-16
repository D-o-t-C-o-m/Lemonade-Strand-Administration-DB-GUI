package org.mike.service;

import java.util.List;
import org.mike.domain.Product;
import org.mike.exceptions.IDNotUniqueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mike.DAO.productDAO;


public class ProductServer{
private static final Logger logger = LoggerFactory.getLogger(ProductServer.class);
private final productDAO dao;

public ProductServer() throws IDNotUniqueException {
	this.dao = new productDAO();
}

public Product save (Product product) {
	logger.info("Saving product{}", product);
	dao.saveProduct(product);
	return product;
}

public Product update(Product product) {
	logger.info("updating product{}", product);
	dao.updateProduct(product);
	return product;
}

public void delete(int entityId) {
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

