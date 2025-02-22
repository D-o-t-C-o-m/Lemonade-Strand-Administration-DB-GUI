package org.mike.domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@jakarta.persistence.Entity
@Table(name = "lemonade_recipes")
@Cacheable
public class LemonadeRecipe extends Entity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "RecipeID")
private int id;

@ElementCollection
@CollectionTable(
		name = "lemonade_recipes",
		joinColumns = @JoinColumn(name = "RecipeID")
)
@ManyToMany
@JoinTable(
		name = "lemonade_recipes",
		joinColumns = @JoinColumn(name = "RecipeID"),
		inverseJoinColumns = @JoinColumn(name = "ProductID")
)
private Map<Product, Integer> productQuantities = new HashMap<>();

@ManyToOne
@JoinColumn(name = "LemonadeID")
private Lemonade lemonade;


public LemonadeRecipe() {}

public LemonadeRecipe(int id, Lemonade lemonade) {
	this.id = id;
	this.lemonade = lemonade;
}

@Override
public int getId() {
	return id;
}

public Map<Product, Integer> getProductQuantities() {
	return productQuantities;
}

//getProducts() method

public Lemonade getLemonade() {
	return lemonade;
}

public void setLemonade(Lemonade lemonade) {
	this.lemonade = lemonade;
}

public void addProduct(Product product, int quantity) {
	productQuantities.put(product, productQuantities.getOrDefault(product, 0) + quantity);
}

@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null || getClass() != obj.getClass()) return false;

	LemonadeRecipe other = (LemonadeRecipe) obj;
	return Objects.equals(lemonade, other.lemonade) && Objects.equals(productQuantities, other.productQuantities);
}

@Override
public int hashCode() {
	return Objects.hash(lemonade, productQuantities);
}
}
