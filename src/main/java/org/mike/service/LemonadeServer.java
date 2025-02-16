package org.mike.service;

import org.mike.DAO.lemonadeDAO;
import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;

import java.util.List;

public class LemonadeServer extends GenericServer<Lemonade> {
	public LemonadeServer() {
		super(new lemonadeDAO());
	}

public List<LemonadeRecipe> findLemonadeRecipe(int lemonadeId) {
	return null;
}

public List<LemonadeRecipe> findAllLemonadeRecipe() {
	return null;
}
}
