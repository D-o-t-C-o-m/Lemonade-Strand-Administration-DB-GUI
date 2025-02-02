package org.mike.repository;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class LemonadeRecipeFileRepository extends GenericRepository<LemonadeRecipe> {
private String filename;
private GenericRepository<Product> productRepository;
private GenericRepository<Lemonade> lemonadeRepository;
private GenericRepository<Supplier> supplierRepository;


public LemonadeRecipeFileRepository(String filename, GenericRepository<Product> productRepository, GenericRepository<Lemonade> lemonadeRepository, GenericRepository<Supplier> supplierRepository) throws IOException, IDNotUniqueException {
	super();
	this.filename = filename;
	this.supplierRepository = supplierRepository;
	this.productRepository = productRepository;
	this.lemonadeRepository = lemonadeRepository;
	super.fileExistenceCheck(filename);
	loadLemonadesFromFile();
}

@Override
public LemonadeRecipe save(LemonadeRecipe lemonadeRecipe) throws IDNotUniqueException, FileNotFoundException {
	LemonadeRecipe savedLemonadeRecipe = super.save(lemonadeRecipe);
	writeToFile();
	return savedLemonadeRecipe;
}

@Override
public LemonadeRecipe update(LemonadeRecipe lemonadeRecipe) throws FileNotFoundException {
	LemonadeRecipe updatedLemonadeRecipe = super.update(lemonadeRecipe);
	writeToFile();
	return updatedLemonadeRecipe;
}

@Override
public void delete(int Id) throws FileNotFoundException {
	super.delete(Id);
	writeToFile();
}

public Map<Integer, LemonadeRecipe> readLemonadeRecipesFromFile() {
	Map<Integer, LemonadeRecipe> recipes = new HashMap<>();
	BufferedReader br;

	try {
		br = new BufferedReader(new FileReader(filename));
		String line;
		br.readLine();

		while ((line = br.readLine()) != null) {
			String[] parts = line.split(",");
			int recipeId = Integer.parseInt(parts[0]); // Recipe ID
			int productId = Integer.parseInt(parts[1]);
			int vendorId = Integer.parseInt(parts[2]);
			int quantity = Integer.parseInt(parts[3]);

			Supplier supplier = supplierRepository.findById(vendorId);


			Product product = productRepository.findById(productId);
			if (product == null) {
				continue;
			}

			Lemonade lemonade = lemonadeRepository.findById(recipeId);
			if (lemonade == null) {
				continue;
			}

			LemonadeRecipe recipe = recipes.get(recipeId);
			if (recipe == null) {
				recipe = new LemonadeRecipe(recipeId, lemonade);
				recipes.put(recipeId, recipe);
			}
			recipe.addProduct(product, quantity);
		}

		br.close();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
	return recipes;
}

private void writeToFile() {
	BufferedWriter bw;
	try {
		bw = new BufferedWriter(new FileWriter(filename));
		bw.write("Recipe ID|Product ID|Lemonade ID|Quantity");
		bw.newLine();

		Iterable<LemonadeRecipe> lemonadeRecipes = findAll();
		for (LemonadeRecipe lemonadeRecipe : lemonadeRecipes) {
			for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {
				Product product = entry.getKey();
				int quantity = entry.getValue();

				String line = lemonadeRecipe.getId() + "," + product.getId() + "," + lemonadeRecipe.getLemonade().getId() + "," + quantity;
				bw.write(line);
				bw.newLine();
			}
		}
		bw.close();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
}

private void loadLemonadesFromFile() throws IDNotUniqueException, IOException {
	Map<Integer, LemonadeRecipe> recipes = readLemonadeRecipesFromFile();
	for (LemonadeRecipe recipe : recipes.values()) {
		this.save(recipe);
	}
}
}

