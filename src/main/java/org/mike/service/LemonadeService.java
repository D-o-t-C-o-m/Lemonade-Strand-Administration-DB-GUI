package org.mike.service;

import org.mike.DAO.lemonadeDAO;
import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;

import java.util.List;

public class LemonadeService extends GenericServer<Lemonade> {
private final lemonadeDAO lemonadeDAO = new lemonadeDAO();
	public LemonadeService() {
		super(new lemonadeDAO());
	}

public List<LemonadeRecipe> findLemonadeRecipe(int lemonadeId) {
	return lemonadeDAO.getRecipe(lemonadeId);
}

public List<LemonadeRecipe> findAllLemonadeRecipe() {
return lemonadeDAO.getAllRecipes();
}
}
