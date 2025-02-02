package org.mike.userinterface;

import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.service.SupplierService;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class SupplierMenu {

private SupplierService supplierService;

public SupplierMenu(SupplierService supplierService) {
	this.supplierService = supplierService;
}

private void showSuppliersMenu() {
	System.out.println("\nSuppliers menu:");
	System.out.println("===================");
	System.out.println("1. Add a supplier");
	System.out.println("2. Update a supplier");
	System.out.println("3. Remove a supplier");
	System.out.println("4. Display all suppliers");
	System.out.println("5. Back to main menu");

	System.out.println("What do you want to do? ");
	System.out.print("> ");
}


public void runSuppliersMenu(Scanner scanner) {
	int option = -1;
	try {
		while (option != 5) {
			showSuppliersMenu();
			option = scanner.nextInt();
			switch (option) {
				case 1:
					handleAddSupplier(scanner);
					break;
				case 2:
					handleUpdateSupplier(scanner);
					break;
				case 3:
					handleRemoveSupplier(scanner);
				case 4:
					handleShowSuppliers();
					break;
				case 5:
					break;
			}
		}
	} catch (InputMismatchException e) {
		System.out.println("Please enter a valid number");
	} catch (FileNotFoundException e) {
		throw new RuntimeException(e);
	}
}

private void handleAddSupplier(Scanner scanner) {
	Random random = new Random();
	int id = random.nextInt(999);
	System.out.println("ID: " + id);
	scanner.nextLine();
	System.out.println("Name: ");
	String name = scanner.nextLine().trim();
	if (name.isEmpty()) {
		System.out.println("Name cannot be empty");
		return;
	}

	System.out.println("Email: ");
	String email = scanner.next().trim();

	try {
		Supplier savedSupplier = supplierService.saveSupplier(id, name, email);
		System.out.printf("The supplier with ID %d was created successfully. %n", savedSupplier.getId());
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the supplier " + e.getMessage());
	} catch (FileNotFoundException e) {
		throw new RuntimeException(e);
	}

}


private void handleUpdateSupplier(Scanner scanner) throws FileNotFoundException {
	System.out.println("ID: ");
	int id = scanner.nextInt();
	scanner.nextLine();
	System.out.println("Name: ");
	String name = scanner.nextLine().trim();

	System.out.println("Email: ");
	String email = scanner.nextLine().trim();

	Supplier updatedSupplier = supplierService.updateSupplier(id, name, email);
	System.out.printf("The supplier with ID %d was updated successfully. %n", updatedSupplier.getId());
}


private void handleRemoveSupplier(Scanner scanner) throws FileNotFoundException {
	System.out.println("ID to Remove: ");
	int id = scanner.nextInt();

	supplierService.deleteSupplier(id);
	System.out.printf("The supplier with ID %d was deleted successfully. %n", id);
}


private void displaySuppliers(Iterable<Supplier> suppliers) {
	for (Supplier supplier : suppliers) {
		System.out.println(supplier);
	}
}

private void handleShowSuppliers() {
	Iterable<Supplier> suppliers = supplierService.findAll();
	displaySuppliers(suppliers);
}

}
