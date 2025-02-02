package org.mike.userinterface;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.repository.LemonadeFileRepository;
import org.mike.repository.LemonadeRecipeFileRepository;
import org.mike.service.LemonadeService;
import org.mike.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LemonadeMenu {
private LemonadeService lemonadeService;
private LemonadeFileRepository lemonadeRepository;
private LemonadeRecipeFileRepository lemonadeRecipeRepository;
private ProductService productService;

public LemonadeMenu(LemonadeService lemonadeService, ProductService productService) {
	this.lemonadeService = lemonadeService;
	this.lemonadeRepository = lemonadeRepository;
	this.lemonadeRecipeRepository = lemonadeRecipeRepository;
	this.productService = productService;
}
public void runLemonadeMenu(Scanner scanner) {
	int option = -1;
	while (option != 3) {
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
				break;

		}
	}
}

private void showLemonadesMenu() {
	System.out.println("\nThe Lemonade menu:");
	System.out.println("====================");
	System.out.println("1. Display all lemonades");
	System.out.println("2. Display the recipe for a lemonade");
	System.out.println("3. Exit");
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
public void lemonadeOutOfStockReport() {
	List<LemonadeRecipe> recipe = lemonadeService.findAllLemonadeRecipe();
	Iterable<Product> onHands = productService.getAllProducts();
	String currentLemonade = "";
	boolean OOS = false;

	for (LemonadeRecipe lemonadeRecipe : recipe) {
		int qtyNeed = 0;
		int qtyOnHand = 0;
		Lemonade current = lemonadeRecipe.getLemonade();
		currentLemonade = current.getName();

		ArrayList<Product> recipeUsage = new ArrayList<Product>();

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
}

