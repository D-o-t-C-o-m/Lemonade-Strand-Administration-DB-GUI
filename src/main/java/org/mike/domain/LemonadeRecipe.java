package org.mike.domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@jakarta.persistence.Entity
@Table(name = "lemonade-recipes")
@Cacheable
public class LemonadeRecipe extends Entity {
@Id
@Column(name = "RecipeID")
private int id;

@ElementCollection
@CollectionTable(name="recipe_products", joinColumns = @JoinColumn(name = "RecipeID"))
@MapKeyJoinColumn(name = "ProductID")
@Column(name = "Quantity")
private Map<Product, Integer> productQuantities;
@ManyToOne
@JoinColumn(name = "LemonadeID")
private Lemonade lemonade;

public LemonadeRecipe(int id, Lemonade lemonade) {
	this.id = id;
	this.lemonade = lemonade;
	this.productQuantities = new HashMap<>();
}

public LemonadeRecipe() {

}

public Map<Product, Integer> getProductQuantities() {
	return productQuantities;
}
public int getQuantity(int id) {
	for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
		if (entry.getKey().getId() == id) {
			return entry.getValue();
		}
	}
	return 0;
}
public Product getProduct(int id) {
	for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
		if (entry.getKey().getId() == id) {
			return entry.getKey();
		}
	}
	return null;
}
public Lemonade getLemonade() {
	return lemonade;
}

public void setLemonade(Lemonade lemonade) {
	this.lemonade = lemonade;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public void addProduct(Product product, int quantity) {
	productQuantities.put(product, productQuantities.getOrDefault(product, 0) + quantity);
}

@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null || getClass() != obj.getClass()) return false;

	LemonadeRecipe other = (LemonadeRecipe) obj;
	return this.lemonade.equals(other.lemonade) && this.productQuantities.equals(other.productQuantities);
}

@Override
public int hashCode() {
	return Objects.hash(lemonade, productQuantities);
}



}