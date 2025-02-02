package org.mike.service;


import org.mike.domain.Product;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.repository.GenericRepository;
import org.mike.validators.ProductValidator;

import java.io.FileNotFoundException;

public class ProductService {
private GenericRepository<Product> productRepository;
private ProductValidator productValidator;
private SupplierService supplierService;

public ProductService(GenericRepository<Product> productRepository, ProductValidator productValidator, SupplierService supplierService) {
	this.productRepository = productRepository;
	this.productValidator = productValidator;
	this.supplierService = supplierService;
}

public Product saveProduct(int Id, String name, String Description, double price, int quantity, int supplierId) throws ValidationException, FileNotFoundException {
	Supplier supplier = supplierService.findById(supplierId);
	Product product = new Product(Id, name, Description, price, quantity, supplier);
	productValidator.validateProduct(product);
	return productRepository.save(product);
}

public void removeProduct(int Id) throws IDNotUniqueException, ValidationException, FileNotFoundException {
	productRepository.delete(Id);
}

public Product updateProduct(int id, String newName, String newDescription, double newPrice, int newQuantity, int newSupplierId) throws ValidationException, FileNotFoundException {
	Supplier supplier = supplierService.findById(newSupplierId);
	removeProduct(id);
	Product productToUpdate = new Product(id, newName, newDescription, newPrice, newQuantity, supplier);

	productValidator.validateProduct(productToUpdate);
	return productRepository.save(productToUpdate);
}

public Iterable<Product> getAllProducts() {
	return productRepository.findAll();
}

public Product findById(int id) {
	return this.productRepository.findById(id);
}
}
