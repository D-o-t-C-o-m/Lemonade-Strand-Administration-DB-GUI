package org.mike.service;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;


public class LemonadeService {
private GenericRepository<Lemonade> lemonadeRepository;
private GenericRepository<LemonadeRecipe> lemonadeRecipeRepository;

public LemonadeService(GenericRepository<LemonadeRecipe> lemonadeRecipeRepository, GenericRepository<Lemonade> lemonadeRepository) {
	this.lemonadeRepository = lemonadeRepository;
	this.lemonadeRecipeRepository = lemonadeRecipeRepository;
}

public List<LemonadeRecipe> findLemonadeRecipe(int lemonadeId) {
	Iterable<LemonadeRecipe> allLemonadeRecipes = lemonadeRecipeRepository.findAll();
	List<LemonadeRecipe> recipeForTheRequestedLemonade = new ArrayList<>();

	for (LemonadeRecipe lemonadeRecipe : allLemonadeRecipes) {
		if (lemonadeRecipe.getLemonade().getId() == lemonadeId) {
			recipeForTheRequestedLemonade.add(lemonadeRecipe);
		}
	}
	return recipeForTheRequestedLemonade;
}

public List<LemonadeRecipe> findAllLemonadeRecipe() {
	Iterable<LemonadeRecipe> allLemonadeRecipes = lemonadeRecipeRepository.findAll();
	List<LemonadeRecipe> recipesForTheLemonades = new ArrayList<>();
	for (LemonadeRecipe lemonadeRecipe : allLemonadeRecipes) {
		{
			recipesForTheLemonades.add(lemonadeRecipe);
		}
	}
	return recipesForTheLemonades;
}

public Lemonade findById(int lemonadeId) {
	return lemonadeRepository.findById(lemonadeId);
}

public Iterable<Lemonade> findAll() {
	return lemonadeRepository.findAll();
}
}
