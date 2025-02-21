package org.mike.Utils;

import org.mike.DAO.DAO;
import org.mike.domain.*;
import org.mike.DAO.productDAO;
import org.mike.DAO.lemonadeDAO;
public class DAOManager {
private final DAO<Order> orderDAO;
private final productDAO productDAO;
private final DAO<Supplier> supplierDAO;
private final lemonadeDAO lemonadeDAO;

public DAOManager() {
	this.orderDAO = new DAO<>(Order.class);
	this.supplierDAO = new DAO<>(Supplier.class);
	this.productDAO = new productDAO();
	this.lemonadeDAO = new lemonadeDAO();
}

public DAO<Order> getOrderDAO() {
	return orderDAO;
}

public productDAO getProductDAO() {
	return productDAO;
}

public DAO<Supplier> getSupplierDAO() {
	return supplierDAO;
}

public lemonadeDAO getLemonadeDAO() {
 return lemonadeDAO;
}
}