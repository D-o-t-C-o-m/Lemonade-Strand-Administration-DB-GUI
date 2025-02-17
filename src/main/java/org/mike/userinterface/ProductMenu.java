package org.mike.userinterface;


import org.mike.domain.Product;
import org.mike.domain.Supplier;
import org.mike.service.GenericServer;
import org.mike.validators.ProductValidator;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.service.SupplierServer;
import org.mike.DAO.productDAO;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProductMenu {
private final productDAO dao = new productDAO();
private final GenericServer<Product> productServer = new GenericServer<>(dao);
private final SupplierServer supplierServer = new SupplierServer();
private final ProductValidator productValidator = new ProductValidator();

public ProductMenu(){

}

private void showProductsMenu() {
	System.out.println("\nProduct menu:");

	System.out.println("===================");
	System.out.println("1. Add a product");
	System.out.println("2. Remove a product");
	System.out.println("3. Update a product");
	System.out.println("4. Update product quantity");
	System.out.println("5. Display all products");
	System.out.println("6. Search for a product by supplier");
	System.out.println("7. Back to main menu");
	System.out.println("What do you want to do? ");
	System.out.print("> ");
}

public void runProductsMenu(Scanner scanner) throws ValidationException, IDNotUniqueException {
	int option = -1;
	while (option != 7) {
		showProductsMenu();
		option = scanner.nextInt();

		switch (option) {
			case 1:
				handleAddProduct(scanner);
				break;
			case 2:
				handleRemoveProducts(scanner);
				break;
			case 3:
				handleUpdateProduct(scanner);
				break;
			case 4:
				handleQuantityUpdate(scanner);
				break;
			case 5:
				handleShowProducts();
				break;
			case 6:
				supplierSearch(scanner);
				break;
			case 7:
				break;
		}
	}
}

private void handleAddProduct(Scanner scanner) {
	Random rand = new Random();
	Product item = new Product();
	int id = rand.nextInt(999);
	System.out.println("ID: " + id);
	item.setProductID(id);
	scanner.nextLine();
	System.out.println("Name: ");
	String name = scanner.nextLine().trim();
	if (name.isEmpty()) {
		System.out.println("Name cannot be empty");
		return;
	}
	item.setName(name);

	System.out.println("Description: ");
	String description = scanner.nextLine().trim();
	item.setDescription(description);

	System.out.println("Price: ");
	double price = scanner.nextDouble();
	item.setPrice(price);

	System.out.println("Quantity: ");
	int quantity = scanner.nextInt();
	item.setQuantity(quantity);

	System.out.println("Supplier Id: ");
	int supplierId = scanner.nextInt();
	Supplier supplier = supplierServer.findById(supplierId);
	item.setSupplier(supplier);
	productValidator.validateProduct(item);

	try {
		productServer.save(item);
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the product " + e.getMessage());
	}
}

private void handleUpdateProduct(Scanner scanner)  {
	System.out.println("ID of product being updated: ");
	int id = scanner.nextInt();
	scanner.nextLine();
	System.out.println("New Name: ");
	String name = scanner.nextLine().trim();

	System.out.println("New Description: ");
	String description = scanner.nextLine().trim();

	System.out.println("New Price: ");
	double price = scanner.nextDouble();

	System.out.println("New Quantity: ");
	int quantity = scanner.nextInt();

	System.out.println("SupplierId: ");
	int supplierId = scanner.nextInt();

	Product updatedProduct = productServer.findById(id);
	updatedProduct.setName(name);
	updatedProduct.setDescription(description);
	updatedProduct.setPrice(price);
	updatedProduct.setQuantity(quantity);
	updatedProduct.setSupplier(supplierServer.findById(supplierId));
	try {
		productServer.update(updatedProduct);
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the product " + e.getMessage());
	}

}
public void handleQuantityUpdate(Scanner scanner) {
	System.out.println("ID of product being updated: ");
	int id = scanner.nextInt();
	scanner.nextLine();
	System.out.println("New Quantity: ");
	int quantity = scanner.nextInt();

	Product updatedProduct = productServer.findById(id);
	updatedProduct.setQuantity(quantity);
	try {
		productServer.update(updatedProduct);
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the product " + e.getMessage());
	}
}

private void handleRemoveProducts(Scanner scanner) throws IDNotUniqueException {
	System.out.println("ID to Remove Products: ");
	int id = scanner.nextInt();

	productServer.delete(id);
	System.out.printf("The product with ID %d was deleted successfully. %n", id);
}

private void handleShowProducts() {
	List<Product> productList = productServer.findAll();
	for (Object product : productList) {
		System.out.println(product);
	}
	System.out.println(" ");
}

private void supplierSearch(Scanner scanner) {
	System.out.println("Supplier to search?");
	System.out.print("> ");
	String search = scanner.next().trim();
	scanner.nextLine();
	System.out.println("Searching for " + "'"+search+"'");
	boolean found = false;

	Iterable<Supplier> suppliers = supplierServer.findAll();

	for (Supplier supplier : suppliers) {
		if (supplier.getName().toLowerCase().contains(search.toLowerCase())) {
			int supplierId = supplier.getId();
			String supplierName = supplier.getName();

			found = true;

			System.out.println("\n"+supplierName+"(Vendor "+supplierId+") :\n");
			Iterable<Product> products = productServer.findAll();
			for (Product product : products) {

				if (product.getSupplier().getId() == supplierId) {
					System.out.println("ID, Name, Description");
					System.out.println(product.getId()+", "+product.getName() + ", " + product.getDescription());

		}
	}
			System.out.println();

			}
		}
	if (!found) {
		System.out.println("No Supplier found with that name.");
	}
	}
}

