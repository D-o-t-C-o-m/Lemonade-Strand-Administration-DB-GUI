package org.mike;

import org.mike.exceptions.IDNotUniqueException;
import org.mike.repository.*;
import org.mike.service.LemonadeService;
import org.mike.service.OrderService;
import org.mike.service.ProductService;
import org.mike.service.SupplierService;
import org.mike.userinterface.GraphicalUI;
import org.mike.userinterface.UserInterface;
import org.mike.validators.ProductValidator;

import java.io.IOException;

public class Main {
public static void main(String[] args) throws IOException, IDNotUniqueException {

	SupplierFileRepository supplierRepository = new SupplierFileRepository("suppliers.csv");
	SupplierService supplierService = new SupplierService(supplierRepository);

	ProductFileRepository productRepository = new ProductFileRepository("products.csv");
	ProductValidator productValidator = new ProductValidator();
	ProductService productService = new ProductService(productRepository, productValidator, supplierService);

	LemonadeFileRepository lemonadeRepository = new LemonadeFileRepository("lemonade.csv");
	LemonadeRecipeFileRepository lemonadeRecipeRepository = new LemonadeRecipeFileRepository("lemonade-recipes.csv",productRepository,lemonadeRepository,supplierRepository);
	LemonadeService lemonadeService = new LemonadeService(lemonadeRecipeRepository,lemonadeRepository);

	OrderFileRepository orderFileRepository = new OrderFileRepository("orders.csv", lemonadeService);
	OrderService orderService = new OrderService(orderFileRepository, lemonadeService, productService);

	UserInterface userInterface = new UserInterface(productService, supplierService, lemonadeService, orderService);



	GraphicalUI graphicalUI = new GraphicalUI(orderService);
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	userInterface.runMenu();

}

}
