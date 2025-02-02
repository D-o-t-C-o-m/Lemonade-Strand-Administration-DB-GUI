package org.mike.userinterface;


import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.service.LemonadeService;
import org.mike.service.OrderService;
import org.mike.service.ProductService;
import org.mike.service.SupplierService;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class UserInterface {
private SupplierMenu supplierMenu;
private ProductMenu productMenu;
private LemonadeMenu lemonadeMenu;
private OrderMenu orderMenu;


public UserInterface(ProductService productService, SupplierService supplierService, LemonadeService lemonadeService, OrderService orderService) {
	this.supplierMenu = new SupplierMenu(supplierService);
	this.productMenu = new ProductMenu(supplierService, productService);
	this.lemonadeMenu = new LemonadeMenu(lemonadeService, productService);
	this.orderMenu = new OrderMenu(orderService);
}

private void showMenu() {
	System.out.println("\nMain Menu:");
	System.out.println("==========");
	System.out.println("1. Manage suppliers");
	System.out.println("2. Manage products");
	System.out.println("3. Manage lemonades recipes");
	System.out.println("4. Create an order");
	System.out.println("5. Daily sales report");
	System.out.println("6. Empty products stock report");
	System.out.println("7. Exit");
	System.out.println("\nWhat do you want to do? ");
	System.out.print("> ");
}

public void runMenu() {
	Scanner scanner = new Scanner(System.in);
	int option = -1;
	try {
		while (option != 7) {
			showMenu();
			option = scanner.nextInt();
			switch (option) {
				case 1:
					supplierMenu.runSuppliersMenu(scanner);
					break;
				case 2:
					productMenu.runProductsMenu(scanner);
					break;
				case 3:
					lemonadeMenu.runLemonadeMenu(scanner);
				case 4:
					orderMenu.runOrderOption(scanner);
					break;
			     case 5:
					orderMenu.runDailyReport();
					break;
				case 6:
					lemonadeMenu.lemonadeOutOfStockReport();
				case 7:
					break;
			}
		}
		scanner.close();
	} catch (InputMismatchException e) {
		System.out.println("Please enter a valid number");
	} catch (ValidationException | FileNotFoundException | IDNotUniqueException e) {
		throw new RuntimeException(e);
	}
}


}
