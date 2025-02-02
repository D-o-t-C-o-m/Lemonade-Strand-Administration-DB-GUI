package org.mike.service;

import org.mike.domain.Product;
import org.mike.domain.Supplier;
import org.mike.exceptions.ValidationException;
import org.mike.repository.GenericRepository;
import org.mike.validators.ProductValidator;

import java.io.FileNotFoundException;

public class ProductServiceTest {

private SupplierService supplierService;
private ProductService productService;

private void setUp() {

	GenericRepository<Supplier> supplierRespository = new GenericRepository<Supplier>();
	supplierService = new SupplierService(supplierRespository);

	GenericRepository<Product> productFileRepository = new GenericRepository<Product>();
	ProductValidator productValidator = new ProductValidator();
	productService = new ProductService(productFileRepository, productValidator, supplierService);

}

public void shouldSaveProduct_whenSavedMethodCalled() throws ValidationException, ValidationException, FileNotFoundException {
	//System.out.println("shouldSaveProduct_whenSavedMethodCalled now running");
	setUp();

	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());

	assert savedProduct != null;
	assert savedProduct.getId() == 1;
	assert savedProduct.getName().equals("Sugar");

	//System.out.println("shouldSaveProduct_whenSavedMethodCalled Passed");
}

public void shouldUpdateProduct_whenUpdateMethodCalled() throws ValidationException, FileNotFoundException {
	//System.out.println("shouldUpdateProduct_whenUpdateMethodCalled now running");
	setUp();
	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());
	Product updatedProduct = productService.updateProduct(1, "Sugar", "Brown sugar", 10, 10, supplier.getId());
	assert updatedProduct != null;
	assert updatedProduct.getId() == 1;
	assert updatedProduct.getDescription().equals("Brown sugar");
	//System.out.println("shouldUpdateProduct_whenUpdateMethodCalled Passed");
}

public void shouldRemoveProduct_whenRemoveMethodCalled() throws ValidationException, FileNotFoundException {
	setUp();
	//System.out.println("shouldRemoveProduct_whenRemoveMethodCalled now running");
	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());
	productService.removeProduct(1);


	assert !productService.getAllProducts().iterator().hasNext();
	//System.out.println("shouldRemoveProduct_whenRemoveMethodCalled Passed");
}

public void testAllProductService() throws ValidationException, FileNotFoundException {
	shouldSaveProduct_whenSavedMethodCalled();
	shouldUpdateProduct_whenUpdateMethodCalled();
	shouldRemoveProduct_whenRemoveMethodCalled();

}


}