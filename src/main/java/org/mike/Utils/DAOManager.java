package org.mike.Utils;

import org.mike.DAO.DAO;
import org.mike.domain.Order;
import org.mike.domain.Product;
import org.mike.domain.Supplier;

public class DAOManager {
private final DAO<Order> orderDAO;
private final DAO<Product> productDAO;
private final DAO<Supplier> supplierDAO;

public DAOManager() {
	this.orderDAO = new DAO<>(Order.class);
	this.productDAO = new DAO<>(Product.class);
	this.supplierDAO = new DAO<>(Supplier.class);
}

public DAO<Order> getOrderDAO() {
	return orderDAO;
}

public DAO<Product> getProductDAO() {
	return productDAO;
}

public DAO<Supplier> getSupplierDAO() {
	return supplierDAO;
}
}