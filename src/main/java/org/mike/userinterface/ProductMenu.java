package org.mike.userinterface;

import org.mike.domain.Product;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.service.ProductService;
import org.mike.service.SupplierService;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProductMenu {
private SupplierService supplierService;
private ProductService productService;

public ProductMenu(SupplierService supplierService, ProductService productService) {
	this.supplierService = supplierService;
	this.productService = productService;
}

private void showProductsMenu() {
	System.out.println("\nProduct menu:");

	System.out.println("===================");
	System.out.println("1. Add a product");
	System.out.println("2. Update a product");
	System.out.println("3. Remove a product");
	System.out.println("4. Display all products");
	System.out.println("5. Search for a product by supplier");
	System.out.println("6. Back to main menu");
	System.out.println("What do you want to do? ");
	System.out.print("> ");
}

public void runProductsMenu(Scanner scanner) throws FileNotFoundException, ValidationException, IDNotUniqueException {
	int option = -1;
	while (option != 6) {
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
				handleShowProducts();
				break;
			case 5:
				supplierSearch(scanner);
				break;
			case 6:
				break;
			//Add a method for updating product quantites IE Purchase / Waste
		}
	}
}

private void handleAddProduct(Scanner scanner) {
	System.out.println("Product ID: ");
	int id = scanner.nextInt();
	scanner.nextLine();
	System.out.println("Name: ");
	String name = scanner.nextLine().trim();

	System.out.println("Description: ");
	String description = scanner.nextLine().trim();

	System.out.println("Price: ");
	double price = scanner.nextDouble();

	System.out.println("Quantity: ");
	int quantity = scanner.nextInt();

	System.out.println("Supplier Id: ");
	int supplierId = scanner.nextInt();

	try {
		productService.saveProduct(id, name, description, price, quantity, supplierId);
	} catch (ValidationException | IDNotUniqueException | FileNotFoundException e) {
		System.out.println("Error with saving the product " + e.getMessage());
	}
}

private void handleUpdateProduct(Scanner scanner) throws FileNotFoundException {
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

	try {
		productService.updateProduct(id, name, description, price, quantity, supplierId);
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the product " + e.getMessage());
	}

}

private void handleRemoveProducts(Scanner scanner) throws FileNotFoundException, ValidationException, IDNotUniqueException {
	System.out.println("ID to Remove Products: ");
	int id = scanner.nextInt();

	productService.removeProduct(id);
	System.out.printf("The product with ID %d was deleted successfully. %n", id);
}

private void handleShowProducts() {
	Iterable<Product> productList = productService.getAllProducts();
	for (Product product : productList) {
		System.out.println(product);
	}
}

private void supplierSearch(Scanner scanner) {
	System.out.println("Supplier to search?");
	System.out.print("> ");
	String search = scanner.next().trim();
	scanner.nextLine();
	System.out.println("Searching for " + "'"+search+"'");
	boolean found = false;

	Iterable<Supplier> suppliers = supplierService.findAll();

	for (Supplier supplier : suppliers) {
		if (supplier.getName().toLowerCase().contains(search.toLowerCase())) {
			int supplierId = supplier.getId();
			String supplierName = supplier.getName();

			found = true;

			System.out.println("\n"+supplierName+":\n");
			Iterable<Product> products = productService.getAllProducts();
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

