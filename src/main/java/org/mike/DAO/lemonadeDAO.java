package org.mike.DAO;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;


import java.util.List;
import java.util.Map;


public class lemonadeDAO extends DAO<Lemonade> {
public lemonadeDAO() {
	super(Lemonade.class);
}

public List<LemonadeRecipe> getAllRecipes() {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	List<LemonadeRecipe> recipes = session.createQuery("SELECT r FROM LemonadeRecipe r", LemonadeRecipe.class).getResultList();
	tx.commit();
	session.close();
	return recipes;
}

public void printAllRecipes() {
	List<LemonadeRecipe> recipes = getAllRecipes();

	for (LemonadeRecipe recipe : recipes) {
		System.out.println("Recipe ID: " + recipe.getId());
		for (Map.Entry<Product, Integer> entry : recipe.getProductQuantities().entrySet()) {
			System.out.println("   - " + entry.getKey().getName() + ": " + entry.getValue());
		}
	}

}
public List<LemonadeRecipe> getRecipe(int lemonadeId) {
return null;
}

}