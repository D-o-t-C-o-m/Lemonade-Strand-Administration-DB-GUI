package org.mike.Utils;

import org.mike.service.GenericServer;
import org.mike.service.LemonadeService;
import org.mike.domain.Order;
import org.mike.domain.Product;
import org.mike.domain.Supplier;

public class ServerManager {
private final GenericServer<Order> orderService;
private final GenericServer<Product> productService;
private final GenericServer<Supplier> supplierService;
private final LemonadeService lemonadeService;

public ServerManager(DAOManager daoManager) {
	this.orderService = new GenericServer<>(daoManager.getOrderDAO());
	this.productService = new GenericServer<>(daoManager.getProductDAO());
	this.supplierService = new GenericServer<>(daoManager.getSupplierDAO());
	this.lemonadeService = new LemonadeService();
}

public GenericServer<Order> getOrderService() {
	return orderService;
}

public GenericServer<Product> getProductService() {
	return productService;
}

public GenericServer<Supplier> getSupplierService() {
	return supplierService;
}

public LemonadeService getLemonadeService() {
	return lemonadeService;
}
}