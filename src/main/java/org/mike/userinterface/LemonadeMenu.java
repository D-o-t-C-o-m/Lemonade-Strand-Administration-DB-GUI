package org.mike.userinterface;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.DAO.productDAO;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.service.*;
import org.mike.Utils.IdGenerator;
import org.mike.DAO.lemonadeDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LemonadeMenu {

private final LemonadeService lemonadeService = new LemonadeService();
private final GenericServer<Product> productService = new GenericServer<>(new productDAO());
private final IdGenerator<Lemonade> idGenerator;
private final lemonadeDAO lemonadeDAO = new lemonadeDAO();



public LemonadeMenu() {
	this.idGenerator = new IdGenerator<>(lemonadeDAO);
}

public void runLemonadeMenu(Scanner scanner) {
	int option = -1;
	while (option != 5) {
		showLemonadesMenu();
		option = scanner.nextInt();

		switch (option) {
			case 1:
				handleShowLemonades();
				break;
			case 2:
				handleShowLemonadeRecipes(scanner);
				break;
			case 3:
				handleShowAllLemonadeRecipes(scanner);
				break;
			case 4:
				System.out.println("Coming soon!");
				break;
			case 5:
				break;
		}
	}
}
//add create new lemonade
private void showLemonadesMenu() {
	System.out.println("\nThe Lemonade menu:");
	System.out.println("====================");
	System.out.println("1. Display all lemonades");
	System.out.println("2. Display the recipe for a lemonade");
	System.out.println("3. Display recipes for all lemonades");
	System.out.println("4. Create a new Lemonade");
	System.out.println("5. Exit");
	System.out.println("What do you want to do? ");
	System.out.print("> ");
}
private void handleShowLemonades() {
	Iterable<Lemonade> lemonades = lemonadeService.findAll();
	for (Lemonade lemonade : lemonades) {
		System.out.println(lemonade.getName());
	}
}
private void handleShowLemonadeRecipes(Scanner scanner) {
	System.out.print("The ID of the lemonade: ");
	int lemonadeId = scanner.nextInt();

	List<LemonadeRecipe> requestedLemonadeRecipe = lemonadeService.findLemonadeRecipe(lemonadeId);
	System.out.println("The requested lemonade contains: ");

	for (LemonadeRecipe lemonadeRecipe : requestedLemonadeRecipe) {
		for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {

			Product product = entry.getKey();
			int quantity = entry.getValue();

			System.out.println(quantity + " " + product.getName());
		}
	}
}
private void handleShowAllLemonadeRecipes(Scanner scanner) {
lemonadeService.findAllLemonadeRecipe();

}
public void lemonadeOutOfStockReport() {
	List<LemonadeRecipe> recipe = lemonadeService.findAllLemonadeRecipe();
	boolean OOS = false;

	for (LemonadeRecipe lemonadeRecipe : recipe) {
		int qtyNeed = 0;
		int qtyOnHand = 0;
		Lemonade current = lemonadeRecipe.getLemonade();
		String currentLemonade = current.getName();

		ArrayList<Product> recipeUsage = new ArrayList<>();

		for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {

			Product product = entry.getKey();
			int quantity = entry.getValue();
			qtyNeed += quantity;
			recipeUsage.add(product);
		}

		for (Product product : recipeUsage) {
			int quantity = product.getQuantity();
			qtyOnHand += quantity;
		}
		if (qtyNeed > qtyOnHand) {
			OOS = true;
			System.out.println(currentLemonade + " does not have enough ingredients to be made at this time.");
		}
	}
	if (!OOS) {
		System.out.println("We have everything we need to make all of our Lemonade");
	}
}

public void addRecipe() {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();

	int id = idGenerator.generateId();

	System.out.println("Enter the name of your new Lemonade: ");
	String name = new Scanner(System.in).nextLine();

	System.out.println("Enter the price of your new Lemonade: ");
	double price = new Scanner(System.in).nextDouble();

	System.out.println("Here are the products available to add to your recipe: ");
	List<Product> productList = productService.findAll();
	for (Object product : productList) {
		System.out.println(product);
	}
	System.out.println("Enter the ID of the products you would like to add to the recipe one at a time. Enter 0 to finish.");
	ArrayList<Product> recipeProducts = new ArrayList<>();
	ArrayList<Integer> recipeQuantities = new ArrayList<>();
	int productId = 0;
	int quantity = 0;
	while (productId != 0) {
		productId = new Scanner(System.in).nextInt();
		if (productId != 0) {
			Product product = productService.findById(productId);
			recipeProducts.add(product);
			System.out.println("Enter the quantity of the product: ");
			quantity = new Scanner(System.in).nextInt();
			recipeQuantities.add(quantity);
		}
	}


	Lemonade lemonade = new Lemonade(id, name, price);
	session.persist(lemonade);

	LemonadeRecipe recipe = new LemonadeRecipe();
	recipe.setLemonade(lemonade);
	//Add products to recipe
	//Add quantities to products

	session.persist(recipe);

	tx.commit();
	session.close();
}
}


